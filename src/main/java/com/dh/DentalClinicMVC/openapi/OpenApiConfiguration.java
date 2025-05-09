package com.dh.DentalClinicMVC.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Dental Clinic - Open Api Documentation",
                version = "1.0",
                description = "API REST para la gestión interna de una clínica odontológica. Soporta operaciones CRUD sobre pacientes, odontólogos y turnos, junto con login seguro mediante JWT. " +
                        "\n\n 🔐 Login con JWT.\n" +
                        "\n 👤 Roles: USER y ADMIN.\n" +
                        "\n 📁 [Repositorio en GitHub](https://github.com/SofiaJuego/DentalClinic-MVC)"
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {

}
