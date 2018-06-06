package com.sunqubit.faqture.rest.webs;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunqubit.faqture.beans.utils.StoreManager;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value="/zip", produces="application/zip")
    public void zipFiles(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");
        try {
        	InputStream in = StoreManager.getFileStore("6-20603088981/sunat/envio/2018/MAYO/20603088981-01-F002-00001.xml");
        	//URL url = new URL("https://i.stack.imgur.com/UFfaV.png");
        	//InputStream in = url.openStream();
        	
        	/*byte[] buffer = new byte[2048];
        	try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
        		ZipEntry entry = new ZipEntry("archivo.png");
                zipOut.putNextEntry(entry);
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, len);
                }
                zipOut.closeEntry();
            }*/
        	byte[] buffer2 = new byte[2048];
        	ByteArrayOutputStream out = new ByteArrayOutputStream();
        	try (ZipOutputStream zipOut2 = new ZipOutputStream(out)) {
        		ZipEntry entry2 = new ZipEntry("archivo.png");
                zipOut2.putNextEntry(entry2);
                int len = 0;
                while ((len = in.read(buffer2)) != -1) {
                    zipOut2.write(buffer2, 0, len);
                }
                zipOut2.closeEntry();
            }
            StoreManager.saveXMLZipStore(out.toByteArray(), "6-20603088981/test.zip");
            response.getOutputStream().close();
            in.close();
        } catch (Exception e) {
        	e.printStackTrace();
		}
    }
}
