package com.study.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 自定义name strategy
 * 1.列的驼峰命名改成下划线；
 * 2.替换指定关键字
 * @author all
 *
 */
public class CustomePhysicalNamingStrategy implements PhysicalNamingStrategy {


	private static final Map<String,String> ABBREVIATIONS = buildAbbreviationMap();

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// Acme naming standards do not apply to catalog names
		return name;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// Acme naming standards do not apply to schema names
		return name;
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
//		final List<String> parts = splitAndReplace( name.getText() );
//		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
//				join( parts ),
//				name.isQuoted()
//		);
		return name;
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		final LinkedList<String> parts = splitAndReplace( name.getText() );
		// Acme Corp says all sequences should end with _seq
		if ( !"seq".equalsIgnoreCase( parts.getLast() ) ) {
			parts.add( "seq" );
		}
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				join( parts ),
				name.isQuoted()
		);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		final List<String> parts = splitAndReplace( name.getText() );
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				join( parts ),
				name.isQuoted()
		);
	}

	private static Map<String, String> buildAbbreviationMap() {
		TreeMap<String,String> abbreviationMap = new TreeMap<> ( String.CASE_INSENSITIVE_ORDER );
//		abbreviationMap.put( "account", "acct" );
//		abbreviationMap.put( "number", "num" );
		return abbreviationMap;
	}
	//以驼峰字母分割返回数组,并把分割组中大小变小写
	//比如：fooBar -->[foo,bar]
	private LinkedList<String> splitAndReplace(String name) {
		LinkedList<String> result = new LinkedList<>();
		for ( String part : StringUtils.splitByCharacterTypeCamelCase( name ) ) {
			if ( part == null || part.trim().isEmpty() ) {
				// skip null and space
				continue;
			}
			part = applyAbbreviationReplacement( part );
			result.add( part.toLowerCase( Locale.ROOT ) );
		}
		return result;
	}

	private String applyAbbreviationReplacement(String word) {
		if ( ABBREVIATIONS.containsKey( word ) ) {
			return ABBREVIATIONS.get( word );
		}

		return word;
	}

	//param List[foo,bar]
	//result:foo_bar
	private String join(List<String> parts) {
		boolean firstPass = true;
		String separator = "";
		StringBuilder joined = new StringBuilder();
		for ( String part : parts ) {
			joined.append( separator ).append( part );
			if ( firstPass ) {
				firstPass = false;
				separator = "_";
			}
		}
		return joined.toString();
	}
}
