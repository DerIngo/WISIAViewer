package deringo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import deringo.wisia.art.Art;
import deringo.wisia.art.WISIAExtraktorService;

public class ArtService {
    public final static String REGELWERK_BV  = "BArtSchV Novellierung [BV]";
    public final static String REGELWERK_BG  = "streng bzw. besonders geschützt nach BNatSchG [BG]";
    public final static String REGELWERK_IAS = "EG-Verordnung IAS 2016/1141 [IAS]";
    public final static String REGELWERK_WA  = "Washingtoner Artenschutzübereinkommen [WA]";
    public final static String REGELWERK_VSR = "Vogelschutzrichtlinie 08/2018 Art.1 [VSR]";
    public final static String REGELWERK_FFH = "FFH-Richtlinie EG 2013/17 [FFH]";
    public final static String REGELWERK_EG  = "Verordnung (EU) 2021/2280 [EG]";
    
    public final static String ANHANG_BV_1   = "Anhang:1";
    public final static String STATUS_BG_b   = "Status:b";
    public final static String STATUS_BG_s   = "Status:s";
    public final static String ANHANG_IAS    = "Anhang:IAS";
    public final static String ANHANG_WA_I     = "Anhang:I";
    public final static String ANHANG_WA_II    = "Anhang:II";
    public final static String ANHANG_WA_III   = "Anhang:III";
    public final static String ANHANG_VSR_1    = "Anhang:Art.1";
    public final static String ANHANG_FFH_II   = "Anhang:II";
    public final static String ANHANG_FFH_IV   = "Anhang:IV";
    public final static String ANHANG_EG_A   = "Anhang:A";
    public final static String ANHANG_EG_B   = "Anhang:B";
    public final static String ANHANG_EG_C   = "Anhang:C";
    public final static String ANHANG_EG_D   = "Anhang:D";

    
    private static List<Art> alleArten = null;
    
    public static List<Art> getAlleArten() {
        if (alleArten == null) {
            alleArten = WISIAExtraktorService.loadAlleArten();
        }
        return alleArten;
    }
    
    public static Art getArtById(int knotenId) {
        Art art = getAlleArten().stream()
                .filter(aArt -> (aArt.getKnoten_id() == knotenId))
                .findFirst()
                .orElse(null);
        return art;
    }
    
    public static List<Art> searchByWissenschaftlicherName(String text) {
         if (StringUtils.isBlank(text)) {
            return new ArrayList<Art>();
        }
        List<Art> gesuchteArten = getAlleArten().stream()
                .filter(art -> StringUtils.containsIgnoreCase(art.getWissenschaftlicherName(), text))
                .toList();
        
        return gesuchteArten;
    }
}
