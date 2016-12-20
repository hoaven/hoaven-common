package com.hoaven.common.util;

import java.io.Serializable ;
import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger ;
import java.util.regex.Pattern;

/**
 * 公用工具
 * 
 * @author mike
 * 
 */

public class UtilTools
{

	private UtilTools()
	{
		// empty
	}

	private static final Pattern PAIR_SPLITTER = Pattern.compile(">>");
	public static String[] splitPair(CharSequence pairStr){
		if(pairStr==null || pairStr.length()==0){
			return new String[0];
		}else {
			return PAIR_SPLITTER.split(pairStr);
		}
	}

	/**
	 * <pre>
	 * Check if the object is empty.
	 * if the object is string, check null and empty string
	 * if the object is collection or Map, check null and empty size
	 * other object, check null.
	 * </pre>
	 * 
	 * @param o
	 * @return true if the object is empty base on above rules.
	 */
	public static boolean isEmpty( Object o )
	{

		if ( o == null )
		{
			return true ;
		}
		else if ( o instanceof String )
		{
			return "".equals(o) ;
		}
		else if ( o instanceof Collection<?> )
		{
			return ( (Collection<?>) o ).size() == 0 ;
		}
		else if ( o instanceof Map<?, ?> )
		{
			return ( (Map<?, ?>) o ).size() == 0 ;
		}
		else if ( o instanceof Object[] )
		{
			return ( (Object[]) o ).length == 0 ;
		}

		return false ;
	}

	public static Object convertNullToEmptyString(Object object){
		return object==null?"":object;
	}

	public static boolean isNumberString(CharSequence str){
		if(str == null || str.length()==0){
			return false;
		}else {
			for(int i=0;i<str.length();i++){
				char ch = str.charAt(i);
				if((ch<'0' || ch>'9') && ch!='.')
					return false;
			}
		}
		return true;
	}

	public static boolean isDigitalString(CharSequence str){
		if(str == null || str.length()==0){
			return false;
		}else {
			for(int i=0;i<str.length();i++){
				char ch = str.charAt(i);
				if(ch<'0' || ch>'9')
					return false;
			}
		}
		return true;
	}

	/**
	 * Cast the object to specified type.
	 * 
	 * It would throw ClassCastException if the type do not match.
	 * 
	 * @param <T>
	 *            reference type
	 * @param obj
	 *            cast object
	 * @return Reference type specified object
	 */

	@SuppressWarnings( "unchecked" )
	public static <T> T uncheckedCast( Object obj )
	{
		return (T) obj ;
	}

	/**
	 * Cast object to map ignore unchecked warning.
	 * 
	 * @param <T>
	 *            return type
	 * @param obj
	 *            object to be cast
	 * @return type specified map object
	 */
	@SuppressWarnings( "unchecked" )
	public static <T> T uncheckedMapCast( Object obj )
	{
		if ( obj instanceof Map<?, ?> )
		{
			return (T) obj ;
		}
		else
		{
			return null ;
		}
	}

	/**
	 * Cast object to collection ignore unchecked warning.
	 * 
	 * @param <T>
	 *            return type
	 * @param obj
	 *            object to be cast
	 * @return type specified collection object
	 */
	@SuppressWarnings( "unchecked" )
	public static <T> T uncheckedCollectionCast( Object obj )
	{
		if ( obj instanceof Collection<?> )
		{
			return (T) obj ;
		}
		else
		{
			return null ;
		}
	}

	/**
	 * Cast object to set ignore unchecked warning.
	 * 
	 * @param <T>
	 *            return type
	 * @param obj
	 *            object to be cast
	 * @return type specified set object
	 */
	@SuppressWarnings( "unchecked" )
	public static <T> T uncheckedSetCast( Object obj )
	{
		if ( obj instanceof Set<?> )
		{
			return (T) obj ;
		}
		else
		{
			return null ;
		}
	}

	/**
	 * Cast object to list ignore unchecked warning.
	 * 
	 * @param <T>
	 *            return type
	 * @param obj
	 *            object to be cast
	 * @return type specified list object
	 */
	@SuppressWarnings( "unchecked" )
	public static <T> T uncheckedListCast( Object obj )
	{
		if ( obj instanceof List<?> )
		{
			return (T) obj ;
		}
		else
		{
			return null ;
		}
	}

	/**
	 * Cast the object to specified type, if the object type does not match,
	 * return null.
	 * 
	 * @param <T>
	 *            Object type
	 * @param obj
	 *            object to cast
	 * @param c
	 *            specified object class type
	 * @return type specified object
	 */
	public static <T> T castAs( Object obj, Class<T> c )
	{
		return castAs(obj, c, null) ;
	}

	/**
	 * Cast the object to specified type, if the object type does not match,
	 * return the specified default value.
	 * 
	 * @param <T>
	 *            Object type
	 * @param obj
	 *            object to cast
	 * @param c
	 *            specified object class type
	 * @param defaultValue
	 *            default value to return if the object type does not match
	 * @return type specified object
	 */
	public static <T> T castAs( Object obj, Class<T> c, Object defaultValue )
	{
		if ( c.isInstance(obj) )
		{
			return c.cast(obj) ;
		}
		else
		{
			return c.cast(defaultValue) ;
		}
	}

	/**
	 * Cast object as String, if the type not match, return null.
	 * 
	 * @param obj
	 *            the object to be cast
	 * @return boolean value
	 */
	public static String castAsString( Object obj )
	{
		return castAs(obj, String.class) ;
	}

	/**
	 * Cast object as boolean, if the type not match, return false.
	 * 
	 * @param obj
	 *            the object to be cast
	 * @return boolean value
	 */
	public static boolean castAsBoolean( Object obj )
	{
		return castAs(obj, Boolean.class, false) ;
	}

	/**
	 * Cast object as Integer, if the type not match, return 0.
	 * 
	 * @param obj
	 *            the object to be cast
	 * @return int value
	 */
	public static Integer castAsInteger( Object obj )
	{
		return castAs(obj, Integer.class, 0) ;
	}

	/**
	 * Cast object as Long, if the type not match, return 0.
	 * 
	 * @param obj
	 *            the object to be cast
	 * @return int value
	 */
	public static Long castAsLong( Object obj )
	{
		return castAs(obj, Long.class, 0) ;
	}

	/**
	 * Force cast to the specified object, if type not match, throw
	 * RuntimeException
	 * 
	 * @param <T>
	 *            parameter
	 * @param obj
	 *            parameter to be cast
	 * @param c
	 * @return type specified object
	 */
	public static <T> T forceCast( Object obj, Class<T> c )
	{
		if ( c.isInstance(obj) )
		{
			return c.cast(obj) ;
		}
		else if ( obj == null )
		{
			return null ;
		}
		else
		{
			throw new RuntimeException("Object: " + obj
					+ " does not match the type: " + c.getCanonicalName()) ;
		}
	}

	public static int parseAsInt( Object val )
	{
		return parseAsInt(val, 0) ;
	}

	public static int parseAsInt( Object obj, int defaultVal )
	{
		int val ;

		if ( obj instanceof Number )
		{
			val = ( (Number) obj ).intValue() ;
		}
		else if ( obj instanceof String )
		{
			try
			{
				val = Integer.parseInt((String) obj) ;
			}
			catch ( Exception e )
			{
				val = defaultVal ;
			}

		}
		else
		{
			val = defaultVal ;
		}

		return val ;
	}

	public static long parseAsLong( Object obj )
	{
		return parseAsLong(obj, 0) ;
	}

	public static long parseAsLong( Object obj, long defaultVal )
	{
		long val ;

		if ( obj instanceof Number )
		{
			val = ( (Number) obj ).longValue() ;
		}
		else if ( obj instanceof String )
		{
			try
			{
				val = Long.parseLong((String) obj) ;
			}
			catch ( Exception e )
			{
				val = defaultVal ;
			}

		}
		else
		{
			val = defaultVal ;
		}

		return val ;
	}

	public static float parseAsFloat( Object obj )
	{
		return parseAsFloat(obj, 0) ;
	}

	public static float parseAsFloat( Object obj, float defaultVal )
	{
		float val ;

		if ( obj instanceof Number )
		{
			val = ( (Number) obj ).floatValue() ;
		}
		else if ( obj instanceof String )
		{
			try
			{
				val = Float.parseFloat((String) obj) ;
			}
			catch ( Exception e )
			{
				val = defaultVal ;
			}

		}
		else
		{
			val = defaultVal ;
		}

		return val ;
	}

	public static double parseAsDouble( Object obj, double defaultVal )
	{
		double val ;

		if ( obj instanceof Number )
		{
			val = ( (Number) obj ).doubleValue() ;
		}
		else if ( obj instanceof String )
		{
			try
			{
				val = Double.parseDouble((String) obj) ;
			}
			catch ( Exception e )
			{
				val = defaultVal ;
			}

		}
		else
		{
			val = defaultVal ;
		}

		return val ;
	}

	public static boolean parseAsBoolean( Object obj )
	{
		boolean val ;

		if ( obj instanceof Boolean )
		{
			val = ( (Boolean) obj ).booleanValue() ;
		}
		else if ( obj instanceof String )
		{
			try
			{
				val = Boolean.parseBoolean((String) obj) ;
			}
			catch ( Exception e )
			{
				val = false ;
			}

		}
		else
		{
			val = false ;
		}
		return val ;
	}

	/**
	 * Force cast object to java.io.Serializable
	 * 
	 * @param obj
	 *            the object to be cast
	 * @return Serializable object
	 */
	public static Serializable forceCastToSerializable( Object obj )
	{
		return forceCast(obj, Serializable.class) ;
	}

	@SuppressWarnings( "rawtypes" )
	public static void deepClear( Object obj )
	{
		if ( obj instanceof Map )
		{
			Map mapObj = (Map) obj ;
			for ( Object key : mapObj.keySet() )
			{
				deepClear(key) ;
				deepClear(mapObj.get(key)) ;
			}

			try
			{
				mapObj.clear() ;
			}
			catch ( Exception e )
			{
				// skip it
			}

			mapObj = null ;
		}
		else if ( obj instanceof Collection )
		{
			Collection colObj = (Collection) obj ;
			for ( Object val : colObj )
			{
				deepClear(val) ;
			}

			try
			{
				colObj.clear() ;
			}
			catch ( Exception e )
			{
				// skip it
			}

			colObj = null ;
		}
	}

	public static Date strToDate( String buyDate )
	{
		if ( UtilTools.isEmpty(buyDate) )
		{
			return null ;
		}
		Calendar c = Calendar.getInstance() ;
		if ( Integer.valueOf(buyDate.substring(0, 4)) > 2100 )
		{
			buyDate = c.get(Calendar.YEAR) + buyDate.substring(4) ;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss") ;
		try
		{
			return sdf.parse(buyDate) ;
		}
		catch ( ParseException e )
		{
			return null ;
		}
	}

	public static Date strToDate2( String buyDate )
	{
		if ( UtilTools.isEmpty(buyDate) )
		{
			return null ;
		}
		Calendar c = Calendar.getInstance() ;
		if ( Integer.valueOf(buyDate.substring(0, 4)) > 2100 )
		{
			buyDate = c.get(Calendar.YEAR) + buyDate.substring(4) ;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") ;
		try
		{
			return sdf.parse(buyDate) ;
		}
		catch ( ParseException e )
		{
			return null ;
		}
	}

	public static String dateToStr( Date date )
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss") ;
		return sdf.format(date) ;
	}

	public static String moneyToYuan( Long fen )
	{
		if ( fen == null )
		{
			return "0.00" ;
		}
		if ( fen >= 0 )
		{
			return moneyToYuanForPositive(fen) ;
		}
		else
		{
			return "-" + moneyToYuanForPositive(Math.abs(fen)) ;

		}

	}

	public static String moneyToYuan( Long fen , String moneyUnit)
	{
		if ( fen == null )
		{
			return "0" ;
		}
		if("CNY".equalsIgnoreCase(moneyUnit)) {
			if (fen >= 0) {
				return moneyToYuanForPositiveRound(fen);
			} else {
				return "-" + moneyToYuanForPositive(Math.abs(fen));
			}
		}else {
			return fen.toString();
		}
	}

	public static String moneyToYuanForPositiveRound( Long fen )
	{
		if ( fen == null )
		{
			return "0" ;
		}
		StringBuilder sb = new StringBuilder() ;
		sb.append(fen) ;
		complementFenStr(sb);
		return sb.insert(sb.length() - 2, ".").toString().replace(".00","");
	}

	public static <K,V> String toString(Iterable<Map.Entry<K,V>> iterable){
		if(iterable != null){
			StringBuilder stringBuilder = new StringBuilder();
			Iterator<Map.Entry<K,V>> iterator = iterable.iterator();
			while (iterator.hasNext()){
				Map.Entry<K,V> cur = iterator.next();
				stringBuilder.append(cur.getKey()).append(':').append(cur.getValue()).append(';');
			}
			return stringBuilder.toString();
		}
		return "";
	}

	public static String moneyToYuan( Integer fen )
	{
		if ( fen == null )
		{
			return "0.00" ;
		}
		if ( fen >= 0 )
		{
			return moneyToYuanForPositive(fen) ;
		}
		else
		{
			return "-" + moneyToYuanForPositive(Math.abs(fen)) ;

		}

	}

	public static String moneyToYuanForPositive( Long fen )
	{
		if ( fen == null )
		{
			return "0.00" ;
		}
		StringBuilder sb = new StringBuilder() ;
		sb.append(fen) ;
		complementFenStr(sb);
		return sb.insert(sb.length() - 2, ".").toString() ;
	}

	public static String moneyToYuanForPositive( Integer fen )
	{
		if ( fen == null )
		{
			return "0.00" ;
		}
		StringBuilder sb = new StringBuilder() ;
		sb.append(fen) ;
		complementFenStr(sb);
		return sb.insert(sb.length() - 2, ".").toString() ;
	}

	private static void complementFenStr(StringBuilder sb) {
		int len = sb.length() ;
		if ( len < 3 )
		{
			for ( int i = 0 ; i < 3 - len ; i++ )
			{
				sb.insert(0, "0") ;
			}
		}
	}

	public static String divideTenString( long val )
	{
		if ( val == 0 )
		{
			return "0.0" ;
		}
		StringBuilder sb = new StringBuilder() ;
		sb.append(val) ;
		int len = sb.length() ;
		if ( len < 2 )
		{
			sb.insert(0, "0") ;
		}
		return sb.insert(sb.length() - 1, ".").toString() ;
	}

	public static int stringMultiTen( String val )
	{
		if ( val == null )
		{
			return 0 ;
		}
		String fixed = null ;
		if ( val.indexOf(".") == -1 || val.endsWith(".") )
		{
			fixed = val + "0" ;
		}
		else
		{
			fixed = val.substring(0, val.indexOf(".") + 1) ;
		}
		return Integer.valueOf(fixed) ;
	}

	public static Long yuanToMoney( String yuan )
	{
		if ( yuan == null )
		{
			return null ;
		}
		try
		{
			int pIdx = yuan.indexOf(".") ;
			int len = yuan.length() ;
			String fixed = yuan.replaceAll("\\.", "") ;
			if ( pIdx < 0 || pIdx == len - 1 )
			{
				return Long.valueOf(fixed + "00") ;
			}
			else if ( pIdx == len - 2 )
			{
				return Long.valueOf(fixed + "0") ;
			}
			else
			{
				return Long.valueOf(fixed.substring(0, pIdx + 2)) ;
			}
		}
		catch ( Exception e )
		{
			return null ;
		}
	}

	private static AtomicInteger sequence = new AtomicInteger(0) ;

	public static String simpleUUID()
	{
		long time = System.currentTimeMillis() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss") ;
		StringBuilder uuid = new StringBuilder(sdf.format(new Date(time))) ;
		String mills = String.valueOf(time % 1000) ;
		for ( int i = 0 ; i < 3 - mills.length() ; i++ )
		{
			uuid.append("0") ;
		}
		uuid.append(mills) ;
		String seq = autoSequence() ;
		for ( int i = 0 ; i < 3 - seq.length() ; i++ )
		{
			uuid.append("0") ;
		}
		uuid.append(seq) ;
		return uuid.toString() ;
	}

	private static String autoSequence()
	{
		if ( sequence.incrementAndGet() >= 99 )
			sequence.set(0) ;
		return String.valueOf(sequence.intValue()) ;
	}

}
