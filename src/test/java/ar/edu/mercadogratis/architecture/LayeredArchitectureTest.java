package ar.edu.mercadogratis.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(
        packages = "ar.edu.mercadogratis",
        importOptions = ImportOption.DoNotIncludeTests.class
)

public class LayeredArchitectureTest {
    @ArchTest
    static final ArchRule layeredDependenciesRespected = layeredArchitecture()
            .layer("Controllers").definedBy("ar.edu.mercadogratis.infrastructure.adapters.in.web.controllers..")
            .layer("Services")   .definedBy("ar.edu.mercadogratis.application.service..")
            .layer("Persistence").definedBy(
                    "ar.edu.mercadogratis.infrastructure.adapters.out.persistence..",
                    "ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper.."
            )
            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");
}
