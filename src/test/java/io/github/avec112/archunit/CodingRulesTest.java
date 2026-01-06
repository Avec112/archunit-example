package io.github.avec112.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.github.avec112.Application;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

@AnalyzeClasses(
        packages = "io.github.avec112",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class CodingRulesTest {

    @ArchTest
    static final ArchRule no_system_out_usage =
            noClasses()
                    .that().areNotAssignableTo(Application.class)
                    .should().accessField(System.class, "out")
                    .because("System.out is forbidden except during early bootstrap");

    @ArchTest
    static final ArchRule no_autowiring_on_fields =
            noFields()
                    .should().beAnnotatedWith(org.springframework.beans.factory.annotation.Autowired.class);


    @ArchTest
    static final ArchRule no_transactional_in_ui =
            noClasses()
                    .that().resideInAnyPackage("..ui..")
                    .should().beAnnotatedWith(org.springframework.transaction.annotation.Transactional.class)
                    .because("@Transactional belongs in domain/service, not UI");


}

