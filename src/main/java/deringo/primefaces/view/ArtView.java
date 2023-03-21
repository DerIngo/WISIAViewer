package deringo.primefaces.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import deringo.configuration.Config;
import deringo.service.ArtService;
import deringo.wisia.art.Art;

@ManagedBean
@RequestScoped
public class ArtView implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Art> list = new ArrayList<>();
    
    public List<Art> getArten() {
        if (list.isEmpty()) {
            list = ArtService.getAlleArten();
        }
        return list; 
    }

    public String getBuild() {
        return Config.get("maven.build");
    }
}
