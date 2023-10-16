package br.com.carv.parking.init;

import br.com.carv.parking.enumerations.Role;
import br.com.carv.parking.payload.request.UserPostRequest;
import br.com.carv.parking.service.UserSystemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AdminUserSystem implements CommandLineRunner {

    private final UserSystemService userSystemService;
    private final Logger logger = Logger.getLogger(AdminUserSystem.class.getName());

    public AdminUserSystem(UserSystemService userSystemService) {
        this.userSystemService = userSystemService;
    }
    @Override
    public void run(String... args) throws Exception {
        logger.info("Creating user admin. \nUsername: {admin} \nPassword: {admin}");
        this.userSystemService.save(new UserPostRequest("ADMIN USER", "admin@gmail.com",
                "admin", Role.ROLE_ADMIN, "admin"), null);
    }
}
