package service;

import entity.Commune;
import entity.CommunePart;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.xpath.DefaultXPath;

import java.util.ArrayList;
import java.util.List;

public class Dom4jCommuneService implements CommuneLoadService {
    Document document;
    DefaultXPath communeXPath;
    DefaultXPath communePartXPath;
    public Dom4jCommuneService(String xml){
        try {
            document = DocumentHelper.parseText(xml);
            communeXPath =  new DefaultXPath("//vf:VymennyFormat/vf:Data/vf:Obce/vf:Obec");
            communePartXPath =  new DefaultXPath("//vf:VymennyFormat/vf:Data/vf:CastiObci/vf:CastObce");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Commune> loadCommunes() {
        List<Commune> res = new ArrayList<>();
        communeXPath.selectNodes(document).forEach(commune-> {
            try {
                Long code = Long.parseLong(commune.selectSingleNode("./obi:Kod").getText());
                String name = commune.selectSingleNode("./obi:Nazev").getText();

                res.add(new Commune(code,name));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return res;
    }

    @Override
    public List<CommunePart> loadCommuneParts() {
        List<CommunePart> res = new ArrayList<>();
        communePartXPath.selectNodes(document).forEach(commune-> {
            try {
                Long code = Long.parseLong(commune.selectSingleNode("./coi:Kod").getText());
                String name = commune.selectSingleNode("./coi:Nazev").getText();
                Long communeCode = Long.parseLong(commune.selectSingleNode("./coi:Obec/obi:Kod").getText());

                res.add(new CommunePart(code,name,new Commune(communeCode)));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        return res;
    }
}
