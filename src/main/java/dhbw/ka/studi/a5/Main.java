package dhbw.ka.studi.a5;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger("Main");
        FileHandler fh = new FileHandler("SaveImages-" + sdf.format(System.currentTimeMillis()) + ".log");
        logger.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());

        while (true) {
            saveFile(31);
            logger.info("Picture in 31 saved");
            saveFile(32);
            logger.info("Picture in 32 saved");
            saveFile(33);
            logger.info("Picture in 33 saved");
            saveFile(34);
            logger.info("Picture in 34 saved");
            saveFile(41);
            logger.info("Picture in 41 saved");
            saveFile(42);
            logger.info("Picture in 42 saved");
            saveFile(43);
            logger.info("Picture in 43 saved");
            saveFile(51);
            logger.info("Picture in 51 saved");
            saveFile(52);
            logger.info("Picture in 52 saved");
            saveFile(61);
            logger.info("Picture in 61 saved");
            saveFile(62);
            logger.info("Picture in 62 saved");
            Thread.sleep(60000);
        }
    }

    public static void saveFile(int camNumber) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://www.svz-bw.de/kamera/ftpdata/KA0" + camNumber + "/KA0" + camNumber + "_gross.jpg");
        httpGet.addHeader("Referer", "https://www.svz-bw.de");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity imageEntity = httpResponse.getEntity();

        InputStream is = imageEntity.getContent();
        OutputStream os = new FileOutputStream("images/" + camNumber + "/" + sdf.format(System.currentTimeMillis()) + ".jpg");
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
        httpGet.releaseConnection();

    }
}