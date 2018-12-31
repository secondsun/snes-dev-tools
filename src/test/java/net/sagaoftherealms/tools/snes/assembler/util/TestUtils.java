package net.sagaoftherealms.tools.snes.assembler.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import net.sagaoftherealms.tools.snes.assembler.definition.opcodes.OpCode;
import net.sagaoftherealms.tools.snes.assembler.definition.opcodes.OpCode65816;
import net.sagaoftherealms.tools.snes.assembler.main.InputData;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.SourceParser;

public class TestUtils {

  /**
   * Converts a string to a stream
   *
   * @param inputString a string
   * @return a stream containing inputString
   */
  public static InputStream $(String inputString) {
    return new ByteArrayInputStream(inputString.getBytes());
  }

  public static SourceScanner toScanner(String sourceLine) {
    return toScanner(sourceLine, OpCode65816.opcodes());
  }

  public static SourceScanner toScanner(String sourceLine, OpCode[] opcodes) {
    final String outfile = "test.out";
    final String inputFile = "test.s";
    final int lineNumber = 0;

    var data = new InputData();
    data.includeFile($(sourceLine), inputFile, lineNumber);

    var scanner = data.startRead(opcodes);
    return scanner;
  }

  public static SourceParser asParser(String sourceLine, OpCode[] opcodes) {
    var scanner = toScanner(sourceLine, opcodes);
    return new SourceParser(scanner);
  }

  public static SourceParser asParser(String sourceLine) {
    return asParser(sourceLine, OpCode65816.opcodes());
  }
}
