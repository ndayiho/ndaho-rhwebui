package com.ndaho.rhwebui;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * L’annotation @SpringBootTest permet au contexte Spring d’être chargé lors de l’exécution des tests.
 *
 * Pour exécuter une requête au contrôleur, on utilise un objet MockMvc ; l’attribut correspondant doit être annoté @Autowired pour l'injection de la dépendance. Et la classe doit obligatoirement être annotée @AutoConfigureMockMvc, afin qu’un objet MockMvc soit disponible dans le contexte Spring (ainsi il pourra être injecté dans l’attribut).
 *
 * La méthode perform a en paramètre l’URL à appeler. Puis il s’ensuit une suite d’instructions pour vérifier l’attendu :
 *
 * status().isOk() : la réponse a un code de statut 200 ;
 *
 * view().name(“home”) : le nom de vue retourné correspond au paramètre “home” ;
 *
 * content().string(containsString("Laurent")) : le corps de la réponse contient à un moment ou à un autre le texte Laurent.
 *
 * Notons également que andDo(print()) permet au retour de l’appel d’être affiché dans la console (on y verra donc tout le HTML généré).
 */

@SpringBootTest
@AutoConfigureMockMvc
class RhwebuiApplicationTests {

	@Autowired
	public MockMvc mockMvc;

	@Test
	public void testGetEmployees() throws Exception {
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("rh-home"))
				.andExpect(content().string(containsString("Honore")));
	}

}
