package model.role;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dao.column.ColumnInfoLoader;
import dao.column.ColumnMapper;

/**
 * role 과 column의 대응 정보를 관리 
 *
 */
public class RoleMapper{
	
	private HashMap<String, Map<String, ColumnAccess>> rcMap = new HashMap<>();
	private ColumnInfoLoader columnLoader ;
	
	public RoleMapper(ColumnInfoLoader loader ) {
		this.columnLoader = loader;
	}
	public void loadRoles(InputStream in, String charset) throws ParserConfigurationException, SAXException, IOException {
		
		SAXParserFactory sf = SAXParserFactory.newInstance();
		sf.setNamespaceAware(false);
		SAXParser parser = sf.newSAXParser();
		
		parser.parse(in, new XMLParser());
	}
	
	
	
	class XMLParser extends DefaultHandler {
		
		private String roleName ;
		private HashMap<String, ColumnAccess> mapperList = new HashMap<>();
		
		@Override
		public void endDocument() throws SAXException {
		}

		private void prepareNextParsing(){
			rcMap.put(roleName, mapperList);
			mapperList = new HashMap<>();
		}

		/*
		 * <read columns="*"/>
		 * 
		 * or
		 * 
		 * <read columns="column0,column1"/>
		 */
		private void setReadableColumns(Attributes att , String attName) {
			String value = att.getValue(attName).trim();
			
			if ( "*".equals(value) ) {
				// all list
				Iterator<ColumnMapper> it = columnLoader.getAllMapper().iterator();
				while ( it.hasNext()) {
					ColumnMapper mapper = it.next();
					mapperList.put(mapper.getColumnName(), new ColumnAccess(mapper));
				}
				
			} else {
				// column list
				String [] columnNames = att.getValue(attName).split(",");
				Arrays.asList(columnNames);
				for (int i = 0; i < columnNames.length; i++) {
					ColumnMapper mapper = 
							columnLoader.findColumnMapper(columnNames[i].trim());
					mapperList.put(mapper.getColumnName(), new ColumnAccess(mapper));
				}
			}
		}
		
		/*
		 * <write columns="*" except="소스번호"/>
		 * 
		 */
		private void setWritableColumn(Attributes att ) {
			String valColumns = att.getValue("columns").trim();
			
			String [] columNames = null;
			/* 'column' attribute */
			if ( "*".equals(valColumns)) {
				// can write to all columns
				Iterator<String> it = mapperList.keySet().iterator();
				while ( it.hasNext() ) {
					ColumnAccess acc = mapperList.get(it.next());
					acc.setWritable(true);
				}
			} else {
				columNames = valColumns.split(",");
				for (int i = 0; i < columNames.length; i++) {
					ColumnAccess acc = mapperList.get(columNames[i].trim());
					acc.setWritable(true);
				}
			}
			
			
			/* 'except' attributes */
			if ( att.getIndex("except") < 0 ) {
				return ;
			}
			
			String valExcept = att.getValue("except").trim();
			columNames =valExcept.split(",");
			
			for (int i = 0; i < columNames.length; i++) {
				mapperList.get(columNames[i].trim()).setWritable(false);
			}
		}
		
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if ( "role".equals(qName)) {			
				roleName = attributes.getValue("name");
			} else if ( "read".equals(qName)) {
				setReadableColumns(attributes, "columns");
			} else if ( "write".equals(qName)) {
				setWritableColumn(attributes);
			} else {
				;
			}
		}
		
		



		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if ( "role".equals(qName)) {			
				prepareNextParsing();
			} else if ( "read".equals(qName)) {
				;
			} else if ( "write".equals(qName)) {
				;
			} else {
				;
			}
		}
	}
	public boolean isAccessible(String role, String columnName) {
		return this.rcMap.get(role).containsKey(columnName);
	}
	public ColumnAccess getColumn(String roleName, String columnName) {
		Map<String, ColumnAccess> cMap = this.rcMap.get(roleName);
		ColumnAccess acc = cMap.get(columnName);
		
		if ( acc == null) {
			throw new RuntimeException("cannot find mapper. key : " +columnName);
		}
		return acc;
	}
	

}
