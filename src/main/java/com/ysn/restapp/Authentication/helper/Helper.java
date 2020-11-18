package com.ysn.restapp.Authentication.helper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.ysn.restapp.RestappApplication;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helper {
    @Autowired
    public Environment environment;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public SimpleDateFormat genericDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public SimpleDateFormat genericDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat genericTimeFormat = new SimpleDateFormat("HH:mm:ss");
    public SimpleDateFormat genericDayDiffFormat = new SimpleDateFormat("D");
    public SimpleDateFormat logDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Date getDate() {
        return new Date();
    }

    public String readAttributeFromJarManifest(Path fileName, String attribute) {
        String value = null;

        try (JarFile jarFile = new JarFile(fileName.toString())) {
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) entries.nextElement();
                if (jarEntry.getName().equals("META-INF/build-info.properties")) {
                    log.info(jarEntry.getName() + " found");
                    Properties appProps = new Properties();
                    appProps.load(jarFile.getInputStream(jarEntry));
                    value = appProps.getProperty("build." + attribute);
                    log.info("Value of attribute " + attribute + " : " + value);
                }
            }
        } catch (IOException ex) {
            log.error(ex.toString());
        }
        return value;
    }

    public boolean checkFileOrFolderExists(Path path) {
        boolean result = false;
        if (Files.exists(path)) {
            result = true;
        }
        return result;
    }

    public boolean createDirectory(Path path) {
        boolean result = false;
        if (checkFileOrFolderExists(path)) {
            result = true;
        } else {
            try {
                Files.createDirectories(path.getParent());
                Files.createDirectories(path);
                result = true;
            } catch (IOException ex) {
                log.error(ex.toString());
            }
        }
        return result;
    }

    public Path getFilePath(String type, String location) {

        Path result = null;
        switch (type) {
            case "ajan": {
                result = Paths.get(
                        environment.getProperty("share.folder")
                                + System.getProperty("file.separator")
                                + "ajan" + System.getProperty("file.separator")
                                + location + System.getProperty("file.separator")
                                + "arm-agent.jar").toAbsolutePath().normalize();
                break;
            }
            case "sertifika": {
                result = Paths.get(
                        environment.getProperty("share.folder")
                                + System.getProperty("file.separator")
                                + "sertifika" + System.getProperty("file.separator")
                                + location + System.getProperty("file.separator")
                                + "server.crt").toAbsolutePath().normalize();
                break;
            }
        }
        return result;
    }

    public String getCurrentPath() {
        String result = "";
        ApplicationHome home = new ApplicationHome(RestappApplication.class);
        result = home.getDir().getAbsolutePath();
        return result;
    }

    public PageRequest getPage(String pagenumber, String pageSize) {
        int page = 0;
        int size = 25;
        if (pagenumber != null) {
            page = Integer.parseInt(pagenumber) - 1;
        }

        if (pageSize != null) {
            size = Integer.parseInt(pageSize);
        }

        return PageRequest.of(page, size);
    }

    public boolean isIpInList(List<String> ipList, String ip) {
        boolean result = false;
        for (String xip : ipList) {
            if (xip.equals(ip)) {
                result = true;
            }
        }
        return result;
    }

    public String encPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public boolean matchPassword(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }

    public String genPassword() {
        String dCase = "abcdefghijklmnopqrstuvwxyz";
        String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String sChar = ".:,;!-+*?#";
        String intChar = "0123456789";

        String pass = "";
        Random r = new Random();

        while (pass.length() != 10) {
            int rPick = r.nextInt(4);
            switch (rPick) {
                case 0: {
                    int spot = r.nextInt(25);
                    pass += dCase.charAt(spot);
                    break;
                }
                case 1: {
                    int spot = r.nextInt(25);
                    pass += uCase.charAt(spot);
                    break;
                }
                case 2: {
                    int spot = r.nextInt(9);
                    pass += sChar.charAt(spot);
                    break;
                }
                case 3: {
                    int spot = r.nextInt(9);
                    pass += intChar.charAt(spot);
                    break;
                }
                default:
                    break;
            }
        }

        return pass;
    }

    public List addListUnified(List list, Object obj) {
        if (!list.contains(obj)) {
            list.add(obj);
        }
        return list;
    }

    public String generateLicence() {
        return UUID.randomUUID().toString();
    }

    public String getKalanSure(Date tarih) {
        String result = "Süresi doldu";
        Date now = getDate();
        if (now.before(tarih)) {
            long diff = tarih.getTime() - now.getTime();
            result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " gün "
                    + genericTimeFormat.format(new Date(diff));
        }
        return result;
    }

    public String getCalismaSure(Date tarih) {
        String result = "";
        long diff = tarih.getTime();
        result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " gün "
                + genericTimeFormat.format(new Date(diff));
        return result;
    }

    public Date getDateFromString(String tarih) {
        Date result = null;
        try {
            result = genericDateTimeFormat.parse(tarih);
        } catch (ParseException ex) {
            log.error(ex.toString());
        }
        return result;
    }

    public Date getDateFromLong(Long tarih) {
        return new Date(tarih);
    }

    public Date getMinutesAgo(Integer min) {
        return DateUtils.addMinutes(new Date(), min);
    }

    public double calculateSizeInGB(long originalSize) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format((double) originalSize / (1024.0 * 1024.0 * 1024.0)).replace(',', '.'));
    }

    public double calculateSizeInMB(long originalSize) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format((double) originalSize / (1024.0 * 1024.0)).replace(',', '.'));
    }
}
