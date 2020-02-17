package dk.tka.patientportal;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("dk.tka.patientportal");

        noClasses()
            .that()
                .resideInAnyPackage("dk.tka.patientportal.service..")
            .or()
                .resideInAnyPackage("dk.tka.patientportal.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..dk.tka.patientportal.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
