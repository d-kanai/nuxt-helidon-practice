package io.helidon.examples.quickstart.mp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class DependencyRuleTest {

    @Test
    public void モジュールは別モジュールのexposeパッケージ以外を参照してはならない() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("io.helidon.examples.quickstart.mp");

        //TODO: allモジュールを対象にする
        ArchRule rule = noClasses()
                .that(resideInAPackage("io.helidon.examples.quickstart.mp.modules.inventory.."))
                .and().haveSimpleNameNotEndingWith("Test")
                .should().resideInAPackage("io.helidon.examples.quickstart.mp.user.modules.internal..");

        rule.check(importedClasses);
    }

}