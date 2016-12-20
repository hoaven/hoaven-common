package com.hoaven.template.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtil
{
	private static Configuration cfg = new Configuration();
	private static String path = "/opt/wemart/appconfig/wmplatform/com.hoaven.template";
	private static TemplateLoader templateLoader;

	static
	{
		try
		{
			templateLoader = new FileTemplateLoader(new File(path));
			cfg.setTemplateLoader(templateLoader);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static Template getTemplate(String templateName)
	{
		try
		{
			return cfg.getTemplate(templateName, "UTF-8");
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static String processTemplateIntoString(Template template, Object model)
			throws IOException, TemplateException
	{
		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}

	public static String process(Template template, Object model)
	{
		try
		{
			return processTemplateIntoString(template, model);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过模版文件处理
	 * 
	 * @param templateName
	 *            模版文件名称
	 * @param model
	 * @return
	 */
	public static String process(String templateName, Object model)
	{
		return process(getTemplate(templateName), model);
	}

	/**
	 * 根据模版的内容与参数对象进行解析
	 * 
	 * @param str
	 *            模版内容
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	public static String processByString(String str, Object model) throws IOException
	{
		Template t = new Template(null,str, null);
		return process(t, model);
	}
}