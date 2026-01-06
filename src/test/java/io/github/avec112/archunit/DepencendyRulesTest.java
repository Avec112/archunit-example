package io.github.avec112.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "io.github.avec112")
public class DepencendyRulesTest {

    @ArchTest
    static final ArchRule domain_must_not_depend_on_vaadin =
            noClasses()
                    .that().resideInAnyPackage("..domain..")
                    .should().dependOnClassesThat().resideInAnyPackage("com.vaadin..");

//    @ArchTest
//    static final ArchRule ui_must_not_depend_on_repositories =
//            noClasses()
//                    .that().resideInAnyPackage("..ui..")
//                    .should().dependOnClassesThat().haveSimpleNameEndingWith("Repository")
//                    .because("UI must go via services/application layer, not repositories directly");

    @ArchTest
    static final ArchRule no_entities_in_ui =
            noClasses()
                    .that().resideInAnyPackage("..ui..")
                    .should().dependOnClassesThat().areAnnotatedWith(jakarta.persistence.Entity.class)
                    .because("UI must only use DTOs; JPA entities are domain-only");

    @ArchTest
    static final ArchRule no_repositories_in_ui =
            noClasses()
                    .that().resideInAnyPackage("..ui..")
                    .should().dependOnClassesThat().areAssignableTo(org.springframework.data.repository.Repository.class)
                    .because("Repositories belong in domain, not UI");

}
