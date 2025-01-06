package de.ollie.carp.bm.gui;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ArchitectureTest {

	private static JavaClasses jc = new ClassFileImporter().importPackages("de.ollie.carp.bm.gui");

	@Test
	void shouldNotAccessSwingClasses() {
		noClasses()
			.that()
			.resideInAPackage("de.ollie.carp.bm.gui..")
			.should()
			.accessClassesThat()
			.resideInAPackage("..javax.swing..")
			.check(jc);
	}
}
