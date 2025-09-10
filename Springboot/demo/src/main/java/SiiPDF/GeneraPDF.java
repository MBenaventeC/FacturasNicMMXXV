/*
package SiiPDF;
import jargs.gnu.CmdLineParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import SiiPDF.generatePDF_class;

public class GeneraPDF {

	private static void printUsage() {
		System.err
				.println("Utilice: java cl.nic.dte.examples.GeneraPDF "
						+ "-p <plantilla.xsl> -o <resultado.pdf> <factura.xml>");
	}

	/**
	 * @param args
	 *
	public static void main(String[] args) throws Exception {

		CmdLineParser parser = new CmdLineParser();
		CmdLineParser.Option resultOpt = parser.addStringOption('o', "output");
		CmdLineParser.Option plantillaOpt = parser.addStringOption('p',
				"plantilla");

		try {
			parser.parse(args);
		} catch (CmdLineParser.OptionException e) {
			printUsage();
			System.exit(2);
		}

		String resultS = (String) parser.getOptionValue(resultOpt);
		String plantillaS = (String) parser.getOptionValue(plantillaOpt);

		if (resultS == null || plantillaS == null) {
			printUsage();
			System.exit(2);
		}

		String[] otherArgs = parser.getRemainingArgs();

		if (otherArgs.length != 1) {
			printUsage();
			System.exit(2);
		}

		
		Utilities.generatePDF(new FileInputStream(otherArgs[0]),
				new FileInputStream(plantillaS), new FileOutputStream(
						resultS));

	}

}
*/