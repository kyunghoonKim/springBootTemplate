package com.touchapi.touch;

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
            .importPackages("com.touchapi.touch");

        noClasses()
            .that()
                .resideInAnyPackage("com.touchapi.touch.service..")
            .or()
                .resideInAnyPackage("com.touchapi.touch.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.touchapi.touch.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
