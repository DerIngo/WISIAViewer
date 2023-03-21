package deringo.primefaces.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import deringo.service.ArtService;
import deringo.wisia.art.Art;

@ManagedBean
@RequestScoped
public class ArtDetailView implements Serializable {
    private static final long serialVersionUID = 1L;

    private int knotenId;
    private Art art;
    
    public void onload() {
        art = ArtService.getArtById(knotenId);
    }

    public int getKnotenId() {
        return knotenId;
    }

    public void setKnotenId(int knotenId) {
        this.knotenId = knotenId;
    }

    public Art getArt() {
        return art;
    }

    public void setArt(Art art) {
        this.art = art;
    }
}
