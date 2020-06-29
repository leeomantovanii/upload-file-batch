package com.batch.arquitetura;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTag;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ArchTag("cleanarq")
@AnalyzeClasses(packages = {
        "com.batch.core",
        "com.batch.dataprovider",
        "com.batch.presenter",
}, importOptions = ImportOption.DoNotIncludeTests.class)
@SuppressWarnings("unused")
public class ArquiteturaTest {

    private static final int maximoMetodosPorUsecase = 1;

    @ArchTest
    static final ArchRule usecasesSaoAcessadosPeloMundoExterno =
            Architectures.layeredArchitecture()
                    .layer("Core")
                    .definedBy(
                            "com.batch.core..")
                    .layer("Dataproviders")
                    .definedBy(
                            "com.batch.dataprovider..")
                    .layer("Presenters")
                    .definedBy(
                            "com.batch.presenter..")
                    .whereLayer("Core")
                    .mayOnlyBeAccessedByLayers("Core",
                            "Dataproviders",
                            "Presenters"
                    );

    @ArchTest
    static final ArchRule useCasesDevemPermanecerNoCore =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith("UseCase")
                    .should()
                    .resideInAPackage(
                            "com.batch.core..");


    @ArchTest
    static final ArchRule implementacaoDeGatewaysDevemFicarEmDataprovider =
            Architectures.layeredArchitecture()
                    .layer("Core")
                    .definedBy(
                            "com.batch.core..")
                    .layer("CoreGateways")
                    .definedBy(
                            "com.batch.core.gateway..")
                    .layer("Dataproviders")
                    .definedBy(
                            "com.batch.dataprovider..")
                    .layer("Presenters")
                    .definedBy(
                            "com.batch.presenter..")
                    .whereLayer("CoreGateways")
                    .mayOnlyBeAccessedByLayers("Core",
                            "Dataproviders"
                    );


//    @ArchTest
//    public static ArchRule useCasesDevemTerSomenteUmMetodoPublicoEAnotadosCorretamente =
//            ArchRuleDefinition.classes()
//                    .that()
//                    .haveSimpleNameEndingWith("UseCase")
//                    .and()
//                    .areNotInterfaces()
//                    .should(ArquiteturaTest.temSomenteUmMetodoPublico())
//                    .andShould()
//                    .beAnnotatedWith(UseCase.class)
//                    .because(
//                            "Precisamos garantir que o usecase contém somente uma" +
//                                    " responsabilidade de negócio");

    @ArchTest
    static final ArchRule dataProvidersDevemEstarForaDaCamadaDeDominio =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith(
                            "DataProvider")
                    .should()
                    .resideInAPackage("com.batch.dataprovider..");

    @ArchTest
    static final ArchRule controllersDevemEstarForaDaCamadaDeDominio =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith(
                            "Controller")
                    .should()
                    .resideInAPackage(
                            "com.batch.presenter..");

    @ArchTest
    static final ArchRule camadaDeDominioNaoPodeChamarImplementacoesDeGateways =
            Architectures.layeredArchitecture()
                    .layer("Core")
                    .definedBy(
                            "com.batch.core..")
                    .layer("Dataproviders")
                    .definedBy(
                            "com.batch.dataprovider..")
                    .layer("Presenters")
                    .definedBy(
                            "com.batch.presenter..")
                    .whereLayer("Dataproviders")
                    .mayOnlyBeAccessedByLayers("Presenters");

    @ArchTest
    static final ArchRule camadaDeDominioNaoPodeChamarPresenters =
            Architectures.layeredArchitecture()
                    .layer("Core")
                    .definedBy(
                            "com.batch.core..")
                    .layer("Presenters")
                    .definedBy(
                            "com.batch.presenter..")
                    .whereLayer("Presenters")
                    .mayNotBeAccessedByAnyLayer();

    @ArchTest
    static final ArchRule tabelasDevemTerAnotacaoEntity =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith("Table")
                    .should()
                    .beAnnotatedWith(Entity.class)
                    .andShould()
                    .beAnnotatedWith(Table.class);

    @ArchTest
    static final ArchRule entidadesNaoDevemApresentarCiclos =
            SlicesRuleDefinition.slices()
                    .matching("..dataprovider.(*)..")
                    .should()
                    .beFreeOfCycles();

    @ArchTest
    static final ArchRule entidadesNaoDevemSerComponentes =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("..dataprovider..")
                    .and()
                    .haveSimpleNameEndingWith("Table")
                    .should()
                    .notBeAnnotatedWith(Component.class);

    @ArchTest
    static final ArchRule controllersDevemSerAnotadosCorretamente =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage(
                            "..rest..")
                    .and()
                    .haveSimpleNameEndingWith(
                            "Controller")
                    .should()
                    .beAnnotatedWith(RestController.class);

    @ArchTest
    static final ArchRule advicesDevemSerAnotadasCorretamente =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage(
                            "..rest..")
                    .and()
                    .haveSimpleNameEndingWith(
                            "Advice")
                    .should()
                    .beAnnotatedWith(ControllerAdvice.class);

    private static ArchCondition<JavaClass> temSomenteUmMetodoPublico() {

        return new ArchCondition<JavaClass>("Somente um método público") {

            @Override
            public void check(final JavaClass clazz, final ConditionEvents events) {

                final String name = clazz.getName();
                final Set<JavaMethod> methodsSet = clazz.getMethods();
                int qtdMetodosPublicos = 0;

                for (final JavaMethod javaMethod : methodsSet) {
                    if (javaMethod.getModifiers()
                            .contains(JavaModifier.PUBLIC)) {
                        qtdMetodosPublicos = qtdMetodosPublicos + 1;
                    }
                }

                if (qtdMetodosPublicos > maximoMetodosPorUsecase) {
                    throw new AssertionError(String.format(
                            "Classe %s contém %d métodos públicos, quando o limite é %d",
                            name,
                            qtdMetodosPublicos,
                            maximoMetodosPorUsecase
                    ));
                }
            }
        };
    }
}