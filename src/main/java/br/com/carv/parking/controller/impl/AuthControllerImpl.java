package br.com.carv.parking.controller.impl;

import br.com.carv.parking.controller.AuthController;
import br.com.carv.parking.jwt.response.TokenResponse;
import br.com.carv.parking.payload.request.LoginPostRequest;
import br.com.carv.parking.service.AuthService;
import br.com.carv.parking.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final ReportService reportService;

    public AuthControllerImpl(AuthService authService, ReportService reportService) {
        this.authService = authService;
        this.reportService = reportService;
    }
    @Override
    public ResponseEntity<TokenResponse> login(LoginPostRequest loginPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.login(loginPostRequest));
    }

    @Override
    public ResponseEntity<Void> getReportUser(HttpServletResponse httpServletResponse) throws IOException {
        byte[] report = reportService.generate();
        httpServletResponse.setContentType(MediaType.APPLICATION_PDF_VALUE);
        httpServletResponse.setHeader("Content-disposition", "inline; filename="
                + System.currentTimeMillis() + ".pdf");
        httpServletResponse.getOutputStream().write(report);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
