package io.github.avec112.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import io.github.avec112.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

@AnalyzeClasses(
        packages = "io.github.avec112",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class CodingRulesTest {

    @ArchTest
    static final ArchRule no_generic_exceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    static final ArchRule no_system_out_usage =
            noClasses()
                    .that().areNotAssignableTo(Application.class)
                    .should().accessField(System.class, "out")
                    .because("System.out is forbidden except during early bootstrap");

    @ArchTest
    static final ArchRule no_autowiring_on_fields =
            noFields()
                    .should().beAnnotatedWith(Autowired.class);


    @ArchTest
    static final ArchRule no_transactional_in_ui =
            noClasses()
                    .that().resideInAnyPackage("..ui..")
                    .should().beAnnotatedWith(Transactional.class)
                    .because("@Transactional belongs in domain/service, not UI");

    @ArchTest
    static final ArchRule no_print_stack_trace =
            noClasses()
                    .should().callMethod(Throwable.class, "printStackTrace")
                    .because("printStackTrace() is forbidden; use a logger instead");


}

