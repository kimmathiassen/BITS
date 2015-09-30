package da.aau.kah.bits.integrationtests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import da.aau.kah.bits.config.PhysicalStorageConfig;
import da.aau.kah.bits.config.GeneralConfig;
import da.aau.kah.bits.exceptions.InvalidDatabaseConfig;
import dk.aau.kah.bits.database.DatabaseHandler;
import dk.aau.kah.bits.experiment.AbstractExperimentHandler;
import dk.aau.kah.bits.experiment.ExperimentFactory;
import dk.aau.kah.bits.helpers.ConfigurationLoader;

public class TPCHqueriesTest {
	private PhysicalStorageConfig databaseConfig;
	private DatabaseHandler databaseHandler;
	private String fileName = "onto0st-fact0st-dim0st.json";
	private GeneralConfig generalConfig;
	private ExperimentFactory experimentFactory;
	private AbstractExperimentHandler experimentHandler;
	
	@Before
	public void setup(){
		try {
			databaseConfig = ConfigurationLoader.loadDatabaseConfig(fileName);
			databaseHandler = new DatabaseHandler(databaseConfig);
			
			generalConfig = GeneralConfig.getInstance();
			generalConfig.setTdbLoaderPath("/usr/local/apache-jena-2.12.1/bin/tdbloader");
			generalConfig.setVerbose(false);
			
			experimentFactory = new ExperimentFactory();
			experimentHandler = experimentFactory.makeEvaluation(databaseHandler, databaseConfig);
			experimentHandler.run();
		} catch (FileNotFoundException e) {
			fail("File not found");
			e.printStackTrace();
		} catch (InvalidDatabaseConfig e) {
			fail("Config is invalid");
			e.printStackTrace();
		} catch (IOException e) {
			fail("File not readable");
			e.printStackTrace();
		}
	}

	@After
	public void teardown(){
		databaseHandler.closeConnection();
	}
	//"queries_that_works":"Query01,Query02,Query03,Query04,Query05,Query07,Query08,Query10,Query11,Query12,Query13,Query14,Query15,Query16,Query17,Query19,Query21,Query22",
	
	@Test
	public void query01() throws IOException {
		String result = experimentHandler.getResultHumanReadable("Query01");
		
		String Query01 = ""+
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
				"=========================================================================================================================================================================================================\n"+
				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		assertEquals(Query01, result);
	}
	
//	@Test
//	public void query02() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query02");
//		System.out.println(result);
//		
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query03() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query03");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query04() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query04");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query05() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query05");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//
//	@Test
//	public void query07() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query07");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//
//	@Test
//	public void query08() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query08");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query10() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query10");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query11() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query11");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query12() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query12");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//
//	@Test
//	public void query13() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query13");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//
//	@Test
//	public void query14() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query14");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//
//	@Test
//	public void query15() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query15");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query16() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query16");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//
//
//	@Test
//	public void query17() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query17");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//
//
//	@Test
//	public void query19() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query19");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query21() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query21");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
//	
//	@Test
//	public void query22() throws IOException {
//		String result = experimentHandler.getResultHumanReadable("Query22");
//		System.out.println(result);
//		String Query01 = ""+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
//				"| l_returnflag | l_linestatus | sum_qty     | sum_base_price        | sum_disc_price        | sum_charge            | avg_qty              | avg_price           | avg_disc               | count_order |\n"+
//				"=========================================================================================================================================================================================================\n"+
//				"| \"A\"          | \"F\"          | 3774200.0e0 | 5.32075388068998E9    | 5.054096266682835E9   | 5.256751331449267E9   | 25.537587116854997e0 | 36002.123829014e0   | 0.05014459706345448e0  | 147790      |\n"+
//				"| \"N\"          | \"F\"          | 95257.0e0   | 1.3373779583999994E8  | 1.271323726512E8      | 1.3228629122944473E8  | 25.30066401062417e0  | 35521.32691633465e0 | 0.0493944223107573e0   | 3765        |\n"+
//				"| \"N\"          | \"O\"          | 7476807.0e0 | 1.0537017864569948E10 | 1.0009731423120052E10 | 1.0409988509034628E10 | 25.544528983898026e0 | 35999.74671612606e0 | 0.050096789512779585e0 | 292697      |\n"+
//				"| \"R\"          | \"F\"          | 3785523.0e0 | 5.3379505264698715E9  | 5.071818532942101E9   | 5.274405503049366E9   | 25.5259438574251e0   | 35994.02921403006e0 | 0.04998927856189752e0  | 148301      |\n"+
//				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
//		assertEquals(Query01, result);
//	}
}