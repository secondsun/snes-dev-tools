package net.sagaoftherealms.tools.snes.assembler.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("65816 Include File")
public class Test65816IncludeData {

  @Test
  public void basicInclude() throws IOException {
    InputData data = new InputData();
    data.includeFile(
        Test65816IncludeData.class.getClassLoader().getResourceAsStream("main.s"), "main.s", 0);

    String expectedOutput =
        IOUtils.toString(
            Test65816IncludeData.class.getClassLoader().getResourceAsStream("preprocess.out"),
            "UTF-8");
    assertEquals(expectedOutput, data.prettyPrint());
  }

  /**
   * This test will manually set the buffers position to a location that the parser would while it
   * was assembling multiple files .
   */
  @Test
  public void multiInclude() throws IOException {

    InputData data = new InputData();
    data.includeFile(
        Test65816IncludeData.class.getClassLoader().getResourceAsStream("main.s"), "main.s", 0);
    data.includeFile(
        Test65816IncludeData.class.getClassLoader().getResourceAsStream("defines.i"),
        "defines.i",
        1);
    data.includeFile(
        Test65816IncludeData.class.getClassLoader().getResourceAsStream("snes_memory.i"),
        "snes_memeory.i",
        2);

    String expectedOutput =
        IOUtils.toString(
            Test65816IncludeData.class.getClassLoader().getResourceAsStream("multiinclude.out"),
            "UTF-8");
    assertEquals(expectedOutput, data.prettyPrint());
  }
}
