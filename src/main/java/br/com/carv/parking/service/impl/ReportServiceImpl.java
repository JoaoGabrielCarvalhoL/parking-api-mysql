package br.com.carv.parking.service.impl;

import br.com.carv.parking.exception.ReportGenericException;
import br.com.carv.parking.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String JASPER_DIRECTORY = "classpath:reports/";
    private final ResourceLoader resourceLoader;
    private final DataSource dataSource;
    private final Map<String, Object> params = new HashMap<>();

    public ReportServiceImpl(ResourceLoader resourceLoader, DataSource dataSource) {
        this.resourceLoader = resourceLoader;
        this.dataSource = dataSource;
    }

    @Override
    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    @Override
    public byte[] generate() {
        try {
            Resource resource = resourceLoader.getResource(JASPER_DIRECTORY.concat("parking.jasper"));
            InputStream stream = resource.getInputStream();
            JasperPrint print = JasperFillManager.fillReport(stream, params, dataSource.getConnection());
            return JasperExportManager.exportReportToPdf(print);
        } catch (IOException | JRException | SQLException e) {
            logger.info("The report could not be generated.");
            throw new ReportGenericException(e.getMessage());
        }
    }


}
