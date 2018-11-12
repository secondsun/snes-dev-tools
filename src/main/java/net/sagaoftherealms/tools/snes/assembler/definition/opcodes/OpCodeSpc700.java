package net.sagaoftherealms.tools.snes.assembler.definition.opcodes;

public class OpCodeSpc700 extends OpCode {

  public static final OpCodeSpc700[] OPCODES = {
      new OpCodeSpc700("ADC A,(X)", 0x86, 0),
      new OpCodeSpc700("ADC A,#x", 0x88, 1),
      new OpCodeSpc700("ADC A,!?+X", 0x95, 2),
      new OpCodeSpc700("ADC A,!?+Y", 0x96, 2),
      new OpCodeSpc700("ADC A,!?", 0x85, 2),
      new OpCodeSpc700("ADC A,[x+X]", 0x87, 1),
      new OpCodeSpc700("ADC A,[x]+Y", 0x97, 1),
      new OpCodeSpc700("ADC A,x+X", 0x94, 1),
      new OpCodeSpc700("ADC A,x", 0x84, 1),
      new OpCodeSpc700("ADC (X),(Y)", 0x99, 0),
      new OpCodeSpc700("ADC x,#x", 0x98, 0xB),
      new OpCodeSpc700("ADC x,x", 0x89, 0xB),
      new OpCodeSpc700("ADDW YA,x", 0x7A, 1),
      new OpCodeSpc700("AND1 C,/x.x", 0x6A, 0xB),
      new OpCodeSpc700("AND1 C,x.x", 0x4A, 0xB),
      new OpCodeSpc700("AND1 x.x,C", 0xCA, 0xB),
      new OpCodeSpc700("AND A,(X)", 0x26, 0),
      new OpCodeSpc700("AND A,#x", 0x28, 1),
      new OpCodeSpc700("AND A,!?+X", 0x35, 2),
      new OpCodeSpc700("AND A,!?+Y", 0x36, 2),
      new OpCodeSpc700("AND A,!?", 0x25, 2),
      new OpCodeSpc700("AND A,[x+X]", 0x27, 1),
      new OpCodeSpc700("AND A,[x]+Y", 0x37, 1),
      new OpCodeSpc700("AND A,x+X", 0x34, 1),
      new OpCodeSpc700("AND A,x", 0x24, 1),
      new OpCodeSpc700("AND (X),(Y)", 0x39, 0),
      new OpCodeSpc700("AND x,#x", 0x38, 0xB),
      new OpCodeSpc700("AND x,x", 0x29, 0xB),
      new OpCodeSpc700("ASL !?", 0x0C, 2),
      new OpCodeSpc700("ASL A", 0x1C, 0),
      new OpCodeSpc700("ASL x+X", 0x1B, 1),
      new OpCodeSpc700("ASL x", 0x0B, 1),

      new OpCodeSpc700("BRK", 0x0F, 0),
      new OpCodeSpc700("BBC x.~,x", 0x03, 0xD),
      new OpCodeSpc700("BBS x.~,x", 0x03, 0xC),
      new OpCodeSpc700("BCC x", 0x90, 0xE),
      new OpCodeSpc700("BCS x", 0xB0, 0xE),
      new OpCodeSpc700("BEQ x", 0xF0, 0xE),
      new OpCodeSpc700("BMI x", 0x30, 0xE),
      new OpCodeSpc700("BNE x", 0xD0, 0xE),
      new OpCodeSpc700("BPL x", 0x10, 0xE),
      new OpCodeSpc700("BRA x", 0x2F, 0xE),
      new OpCodeSpc700("BVC x", 0x50, 0xE),
      new OpCodeSpc700("BVS x", 0x70, 0xE),

      new OpCodeSpc700("CALL !?", 0x3F, 2),
      new OpCodeSpc700("CBNE x+X,x", 0xDE, 0xA),
      new OpCodeSpc700("CBNE x,x", 0x2E, 0xA),
      new OpCodeSpc700("CLR1 x.~", 0x02, 4),
      new OpCodeSpc700("CLR1 x,~", 0x02, 4),
      new OpCodeSpc700("CLRC", 0x60, 0),
      new OpCodeSpc700("CLRP", 0x20, 0),
      new OpCodeSpc700("CLRV", 0xE0, 0),
      new OpCodeSpc700("CMP (X),(Y)", 0x79, 0),
      new OpCodeSpc700("CMP A,(X)", 0x66, 0),
      new OpCodeSpc700("CMP A,#x", 0x68, 1),
      new OpCodeSpc700("CMP A,!?+X", 0x75, 2),
      new OpCodeSpc700("CMP A,!?+Y", 0x76, 2),
      new OpCodeSpc700("CMP A,!?", 0x65, 2),
      new OpCodeSpc700("CMP A,[x+X]", 0x67, 1),
      new OpCodeSpc700("CMP A,[x]+Y", 0x77, 1),
      new OpCodeSpc700("CMP A,x+X", 0x74, 1),
      new OpCodeSpc700("CMP A,x", 0x64, 1),
      new OpCodeSpc700("CMP X,!?", 0x1E, 2),
      new OpCodeSpc700("CMP X,#x", 0xC8, 1),
      new OpCodeSpc700("CMP X,x", 0x3E, 1),
      new OpCodeSpc700("CMP Y,!?", 0x5E, 2),
      new OpCodeSpc700("CMP Y,#x", 0xAD, 1),
      new OpCodeSpc700("CMP Y,x", 0x7E, 1),
      new OpCodeSpc700("CMP x,#x", 0x78, 0xB),
      new OpCodeSpc700("CMP x,x", 0x69, 0xB),
      new OpCodeSpc700("CMPW YA,x", 0x5A, 1),

      new OpCodeSpc700("DAA", 0xDF, 0),
      new OpCodeSpc700("DAA A", 0xDF, 0),
      new OpCodeSpc700("DAS", 0xBE, 0),
      new OpCodeSpc700("DAS A", 0xBE, 0),
      new OpCodeSpc700("DBNZ Y,x", 0xFE, 0xE),
      new OpCodeSpc700("DBNZ x,x", 0x6E, 0xA),
      new OpCodeSpc700("DEC A", 0x9C, 0),
      new OpCodeSpc700("DEC !?", 0x8C, 2),
      new OpCodeSpc700("DEC X", 0x1D, 0),
      new OpCodeSpc700("DEC Y", 0xDC, 0),
      new OpCodeSpc700("DEC x+X", 0x9B, 1),
      new OpCodeSpc700("DEC x", 0x8B, 1),
      new OpCodeSpc700("DECW x", 0x1A, 1),
      new OpCodeSpc700("DI", 0xC0, 0),
      new OpCodeSpc700("DIV YA,X", 0x9E, 0),

      new OpCodeSpc700("EI", 0xA0, 0),
      new OpCodeSpc700("EOR1 C,x.x", 0x8A, 0xB),
      new OpCodeSpc700("EOR A,(X)", 0x46, 0),
      new OpCodeSpc700("EOR A,#x", 0x48, 1),
      new OpCodeSpc700("EOR A,!?+X", 0x55, 2),
      new OpCodeSpc700("EOR A,!?+Y", 0x56, 2),
      new OpCodeSpc700("EOR A,!?", 0x45, 2),
      new OpCodeSpc700("EOR A,[x+X]", 0x47, 1),
      new OpCodeSpc700("EOR A,[x]+Y", 0x57, 1),
      new OpCodeSpc700("EOR A,x+X", 0x54, 1),
      new OpCodeSpc700("EOR A,x", 0x44, 1),
      new OpCodeSpc700("EOR (X),(Y)", 0x59, 0),
      new OpCodeSpc700("EOR x,#x", 0x58, 0xB),
      new OpCodeSpc700("EOR x,x", 0x49, 0xB),

      new OpCodeSpc700("INC !?", 0xAC, 2),
      new OpCodeSpc700("INC A", 0xBC, 0),
      new OpCodeSpc700("INC X", 0x3D, 0),
      new OpCodeSpc700("INC Y", 0xFC, 0),
      new OpCodeSpc700("INC x+X", 0xBB, 1),
      new OpCodeSpc700("INC x", 0xAB, 1),
      new OpCodeSpc700("INCW x", 0x3A, 1),

      new OpCodeSpc700("JMP !?", 0x5F, 2),
      new OpCodeSpc700("JMP [!?+X]", 0x1F, 2),

      new OpCodeSpc700("LSR !?", 0x4C, 2),
      new OpCodeSpc700("LSR A", 0x5C, 0),
      new OpCodeSpc700("LSR x+X", 0x5B, 1),
      new OpCodeSpc700("LSR x", 0x4B, 1),

      new OpCodeSpc700("MOV A,#x", 0xE8, 1),
      new OpCodeSpc700("MOV A,(X)+", 0xBF, 0),
      new OpCodeSpc700("MOV A,(X)", 0xE6, 0),
      new OpCodeSpc700("MOV A,!?+X", 0xF5, 2),
      new OpCodeSpc700("MOV A,!?+Y", 0xF6, 2),
      new OpCodeSpc700("MOV A,!?", 0xE5, 2),
      new OpCodeSpc700("MOV A,X", 0x7D, 0),
      new OpCodeSpc700("MOV A,Y", 0xDD, 0),
      new OpCodeSpc700("MOV A,[x+X]", 0xE7, 1),
      new OpCodeSpc700("MOV A,[x]+Y", 0xF7, 1),
      new OpCodeSpc700("MOV A,x+X", 0xF4, 1),
      new OpCodeSpc700("MOV A,x", 0xE4, 1),
      new OpCodeSpc700("MOV SP,X", 0xBD, 0),
      new OpCodeSpc700("MOV X,A", 0x5D, 0),
      new OpCodeSpc700("MOV X,!?", 0xE9, 2),
      new OpCodeSpc700("MOV X,SP", 0x9D, 0),
      new OpCodeSpc700("MOV X,#x", 0xCD, 1),
      new OpCodeSpc700("MOV X,x+Y", 0xF9, 1),
      new OpCodeSpc700("MOV X,x", 0xF8, 1),
      new OpCodeSpc700("MOV (X)+,A", 0xAF, 0),
      new OpCodeSpc700("MOV (X),A", 0xC6, 0),
      new OpCodeSpc700("MOV [x+X],A", 0xC7, 1),
      new OpCodeSpc700("MOV [x]+Y,A", 0xD7, 1),
      new OpCodeSpc700("MOV !?+X,A", 0xD5, 2),
      new OpCodeSpc700("MOV !?,A", 0xC5, 2),
      new OpCodeSpc700("MOV !?,X", 0xC9, 2),
      new OpCodeSpc700("MOV !?+Y,A", 0xD6, 2),
      new OpCodeSpc700("MOV !?,Y", 0xCC, 2),
      new OpCodeSpc700("MOV Y,!?", 0xEC, 2),
      new OpCodeSpc700("MOV Y,A", 0xFD, 0),
      new OpCodeSpc700("MOV Y,#x", 0x8D, 1),
      new OpCodeSpc700("MOV Y,x+X", 0xFB, 1),
      new OpCodeSpc700("MOV Y,x", 0xEB, 1),
      new OpCodeSpc700("MOV x,A", 0xC4, 1),
      new OpCodeSpc700("MOV x,#x", 0x8F, 0xB),
      new OpCodeSpc700("MOV x,X", 0xD8, 1),
      new OpCodeSpc700("MOV x+X,A", 0xD4, 1),
      new OpCodeSpc700("MOV x+X,Y", 0xDB, 1),
      new OpCodeSpc700("MOV x+Y,X", 0xD9, 1),
      new OpCodeSpc700("MOV x,Y", 0xCB, 1),
      new OpCodeSpc700("MOV x,x", 0xFA, 0xB),
      new OpCodeSpc700("MOVW YA,x", 0xBA, 1),
      new OpCodeSpc700("MOVW x,YA", 0xDA, 1),
      new OpCodeSpc700("MOV1 C,x.x", 0xAA, 0xB),
      new OpCodeSpc700("MUL YA", 0xCF, 0),

      new OpCodeSpc700("NOP", 0x00, 0),
      new OpCodeSpc700("NOT1 x.x", 0xEA, 0xB),
      new OpCodeSpc700("NOTC", 0xED, 0),

      new OpCodeSpc700("OR1 C,/x.x", 0x2A, 0xB),
      new OpCodeSpc700("OR1 C,x.x", 0x0A, 0xB),
      new OpCodeSpc700("OR A,(X)", 0x06, 0),
      new OpCodeSpc700("OR A,#x", 0x08, 1),
      new OpCodeSpc700("OR A,!?+X", 0x15, 2),
      new OpCodeSpc700("OR A,!?+Y", 0x16, 2),
      new OpCodeSpc700("OR A,!?", 0x05, 2),
      new OpCodeSpc700("OR A,[x+X]", 0x07, 1),
      new OpCodeSpc700("OR A,[x]+Y", 0x17, 1),
      new OpCodeSpc700("OR A,x+X", 0x14, 1),
      new OpCodeSpc700("OR A,x", 0x04, 1),
      new OpCodeSpc700("OR (X),(Y)", 0x19, 0),
      new OpCodeSpc700("OR x,#x", 0x18, 0xB),
      new OpCodeSpc700("OR x,x", 0x09, 0xB),

      new OpCodeSpc700("PCALL x", 0x4F, 1),
      new OpCodeSpc700("POP A", 0xAE, 0),
      new OpCodeSpc700("POP PSW", 0x8E, 0),
      new OpCodeSpc700("POP X", 0xCE, 0),
      new OpCodeSpc700("POP Y", 0xEE, 0),
      new OpCodeSpc700("PUSH A", 0x2D, 0),
      new OpCodeSpc700("PUSH PSW", 0x0D, 0),
      new OpCodeSpc700("PUSH X", 0x4D, 0),
      new OpCodeSpc700("PUSH Y", 0x6D, 0),

      new OpCodeSpc700("RET", 0x6F, 0),
      new OpCodeSpc700("RET1", 0x7F, 0),
      new OpCodeSpc700("ROL A", 0x3C, 0),
      new OpCodeSpc700("ROL !?", 0x2C, 2),
      new OpCodeSpc700("ROL x+X", 0x3B, 1),
      new OpCodeSpc700("ROL x", 0x2B, 1),
      new OpCodeSpc700("ROR A", 0x7C, 0),
      new OpCodeSpc700("ROR !?", 0x6C, 2),
      new OpCodeSpc700("ROR x+X", 0x7B, 1),
      new OpCodeSpc700("ROR x", 0x6B, 1),

      new OpCodeSpc700("SBC A,(X)", 0xA6, 0),
      new OpCodeSpc700("SBC A,#x", 0xA8, 1),
      new OpCodeSpc700("SBC A,!?+X", 0xB5, 2),
      new OpCodeSpc700("SBC A,!?+Y", 0xB6, 2),
      new OpCodeSpc700("SBC A,!?", 0xA5, 2),
      new OpCodeSpc700("SBC A,[x+X]", 0xA7, 1),
      new OpCodeSpc700("SBC A,[x]+Y", 0xB7, 1),
      new OpCodeSpc700("SBC A,x+X", 0xB4, 1),
      new OpCodeSpc700("SBC A,x", 0xA4, 1),
      new OpCodeSpc700("SBC (X),(Y)", 0xB9, 0),
      new OpCodeSpc700("SBC x,#x", 0xB8, 0xB),
      new OpCodeSpc700("SBC x,x", 0xA9, 0xB),
      new OpCodeSpc700("SET1 x.~", 0x02, 3),
      new OpCodeSpc700("SET1 x,~", 0x02, 3),
      new OpCodeSpc700("SETC", 0x80, 0),
      new OpCodeSpc700("SETP", 0x40, 0),
      new OpCodeSpc700("SLEEP", 0xEF, 0),
      new OpCodeSpc700("STOP", 0xFF, 0),
      new OpCodeSpc700("SUBW YA,x", 0x9A, 1),

      new OpCodeSpc700("TCALL ~", 0x01, 5),
      new OpCodeSpc700("TCLR1 !?", 0x4E, 2),
      new OpCodeSpc700("TSET1 !?", 0x0E, 2),

      new OpCodeSpc700("XCN A", 0x9F, 0),

      new OpCodeSpc700("E", 0x100, -1)
  };

  public OpCodeSpc700(String op, int hex, int type) {
    super(op, hex, type);
  }
}
