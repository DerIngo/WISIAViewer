package deringo.wisia.art;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deringo.configuration.Config;




/**
 * So war das alles nicht geplant...
 *
 */
public class WISIAExtraktorService {
    private static Logger logger = LoggerFactory.getLogger(WISIAExtraktorService.class);
    
    public static void main(String[] args) {
        List<Art> alleArten = WISIAExtraktorService.loadAlleArten();
        System.out.println(alleArten.size());
        System.out.println(alleArten.get(17));
    }
    
    public static List<Art> loadAlleArten() {
        return loadAlleArtenFromResources();
    }
    
    @SuppressWarnings("unused")
    private static List<Art> loadAlleArtenFromFile() {
        try {
            String filename = Config.get("pfade.pfad_arten");
            FileInputStream fis = new FileInputStream(new File(filename));
            GZIPInputStream gz = new GZIPInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(gz);
            
            @SuppressWarnings("unchecked")
            List<Art> alleArten = (List<Art>) ois.readObject();
            
            ois.close();
            gz.close();
            fis.close();
            
            return alleArten;
            
        } catch (Exception e) {
            logger.error("An error occurred: " + e);
            return null;
        }
    }
    
    @SuppressWarnings("unused")
    private static List<Art> loadAlleArtenFromResources() {
        InputStream inputStream = WISIAExtraktorService.class.getClassLoader().getResourceAsStream("alleArten.obj");
        try {
            GZIPInputStream gz = new GZIPInputStream(inputStream);
            ObjectInputStream ois = new ObjectInputStream(gz);
            
            @SuppressWarnings("unchecked")
            List<Art> art = (List<Art>) ois.readObject();

            gz.close();
            ois.close();
            
            return art;
            
        } catch (IOException | ClassNotFoundException e) {
            logger.error("An error occurred: " + e);
            return null;
        } catch (Exception e) {
            logger.error("An error occurred: " + e);
            return null;
        }
    }
}
