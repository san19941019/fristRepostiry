package com.base.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityUtil {
	
	static {
		Properties pro=new Properties();
		pro.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
		pro.setProperty(Velocity.INPUT_ENCODING, "utf-8");
		pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
		Velocity.init(pro);
	}
	
	public void createFile(String templatePath,String filePath,Map<String,Object> data)  {
		Template template=Velocity.getTemplate(templatePath);
		VelocityContext cv=new VelocityContext();
		
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(filePath));
			template.merge(cv, bw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
