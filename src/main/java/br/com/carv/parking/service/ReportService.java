package br.com.carv.parking.service;

public interface ReportService {

    void addParams(String key, Object value);

    byte[] generate();
}
