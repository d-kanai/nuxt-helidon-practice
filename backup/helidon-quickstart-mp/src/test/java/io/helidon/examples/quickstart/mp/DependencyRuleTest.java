package io.helidon.examples.quickstart.mp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class DependencyRuleTest {

    @Test
    public void モジュールは別モジュールのexposeパッケージ以外を参照してはならない() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("io.helidon.examples.quickstart.mp.modules");

        //TODO: うまく動いていない 違反参照してもfailしない

        List<String> modules = List.of("order", "user", "inventory");
        modules.forEach(moduleA -> {
            modules.forEach(moduleB -> {
                if (Objects.equals(moduleA, moduleB)) return;
                ArchRule rule = noClasses()
                        .that(resideInAPackage("io.helidon.examples.quickstart.mp.modules." + moduleA + ".."))
                        .and().haveSimpleNameNotEndingWith("Test")
                        .should().resideInAPackage("io.helidon.examples.quickstart.mp.modules." + moduleB + ".internal..");

                rule.check(importedClasses);
            });
        });
    }

}