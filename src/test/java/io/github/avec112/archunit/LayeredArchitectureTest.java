package io.github.avec112.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(
        packages = "io.github.avec112",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class LayeredArchitectureTest {

    @ArchTest
    static final ArchRule layered_architecture =
            layeredArchitecture()
                    .consideringAllDependencies()
//                    .consideringOnlyDependenciesInLayers()

                    .layer("UI")
                    .definedBy("..ui..")

                    .layer("Domain")
                    .definedBy("..domain..")

                    .layer("Common")
                    .definedBy("..common..")

                    .whereLayer("UI")
                    .mayNotBeAccessedByAnyLayer()

                    .whereLayer("Domain")
                    .mayOnlyBeAccessedByLayers("UI")

                    .whereLayer("Common")
                    .mayOnlyBeAccessedByLayers("UI", "Domain");
}
