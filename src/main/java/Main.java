import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.xpath.DefaultXPath;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import service.CommuneLoadService;
import service.CommuneWriteSerivce;
import service.Dom4jCommuneService;
import service.HibernateCommuneWriteService;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class Main {

    static final String DESTINATION = "C:\\Users\\natolij\\IdeaProjects\\testdata";
    public static void main(String[] args) throws IOException, DocumentException, XPathExpressionException {
        FileUtils.copyURLToFile(
                new URL(" https://vdp.cuzk.cz/vymenny_format/soucasna/20200930_OB_573060_UZSZ.xml.zip"),
                new File(DESTINATION+"\\xml.zip"),
                3000,
                10000);
        File unzippedXml = unzip(new File(DESTINATION+"\\xml.zip"),DESTINATION);
        String xml = Files.readString(unzippedXml.toPath());

        CommuneWriteSerivce communeWriteSerivce = new HibernateCommuneWriteService();
        CommuneLoadService loadService = new Dom4jCommuneService(xml);

        loadService.loadCommunes().forEach(communePart -> communeWriteSerivce.writeCommune(communePart));
        loadService.loadCommuneParts().forEach(communePart -> communeWriteSerivce.writeCommunePart(communePart));
    }
    static File unzip(File file,String outputDir){
        try (java.util.zip.ZipFile zipFile = new ZipFile(file)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
                ZipEntry entry = entries.nextElement();
                File entryDestination = new File(outputDir,  entry.getName());

                entryDestination.createNewFile();
                if (entry.isDirectory()) {
                    entryDestination.mkdirs();
                } else {
                    entryDestination.getParentFile().mkdirs();
                    try (InputStream in = zipFile.getInputStream(entry);
                         OutputStream out = new FileOutputStream(entryDestination)) {
                        IOUtils.copy(in,out);
                        return entryDestination;
                    }
            }
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
