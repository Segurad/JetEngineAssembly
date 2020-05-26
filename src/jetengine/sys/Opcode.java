package jetengine.sys;

import java.util.HashMap;
import java.util.Map;

enum Opcode {

	ADC_A(Address.REG_A, null, Operation.ADC, 1, "8F", 4),
	ADC_B(Address.REG_B, null, Operation.ADC, 1, "88", 4),
	ADC_C(Address.REG_C, null, Operation.ADC, 1, "89", 4),
	ADC_D(Address.REG_D, null, Operation.ADC, 1, "8A", 4),
	ADC_E(Address.REG_E, null, Operation.ADC, 1, "8B", 4),
	ADC_H(Address.REG_H, null, Operation.ADC, 1, "8C", 4),
	ADC_L(Address.REG_L, null, Operation.ADC, 1, "8D", 4),
	ADC_M(Address.REG_M, null, Operation.ADC, 1, "8E", 4),
	ACI_A(null, null, Operation.ADC, 2, "CE", 7),

	ADD_A(Address.REG_A, null, Operation.ADD, 1, "87", 4),
	ADD_B(Address.REG_B, null, Operation.ADD, 1, "80", 4),
	ADD_C(Address.REG_C, null, Operation.ADD, 1, "81", 4),
	ADD_D(Address.REG_D, null, Operation.ADD, 1, "82", 4),
	ADD_E(Address.REG_E, null, Operation.ADD, 1, "83", 4),
	ADD_H(Address.REG_H, null, Operation.ADD, 1, "84", 4),
	ADD_L(Address.REG_L, null, Operation.ADD, 1, "85", 4),
	ADD_M(Address.REG_M, null, Operation.ADD, 1, "86", 7),
	ADI(null, null, Operation.ADD, 2, "C6", 7),

	ANA_A(Address.REG_A, null, Operation.ANA, 1, "A7", 4),
	ANA_B(Address.REG_B, null, Operation.ANA, 1, "A0", 4),
	ANA_C(Address.REG_C, null, Operation.ANA, 1, "A1", 4),
	ANA_D(Address.REG_D, null, Operation.ANA, 1, "A2", 4),
	ANA_E(Address.REG_E, null, Operation.ANA, 1, "A3", 4),
	ANA_H(Address.REG_H, null, Operation.ANA, 1, "A4", 4),
	ANA_L(Address.REG_L, null, Operation.ANA, 1, "A5", 4),
	ANA_M(Address.REG_M, null, Operation.ANA, 1, "A6", 7),
	ANI(null, null, Operation.ADD, 2, "E6", 7),

	CALL(null, null, Operation.CALL, 3, "CD", 18),
	CC(Address.FLAG_CY, null, Operation.CALL_FLAG_1, 3, "DC", 9),
	CM(Address.FLAG_S, null, Operation.CALL_FLAG_1, 3, "FC", 9), // call if flag S = 1 (call if minus)
	CPE(Address.FLAG_P, null, Operation.CALL_FLAG_1, 3, "EC", 9),
	CZ(Address.FLAG_Z, null, Operation.CALL_FLAG_1, 3, "CC", 9),
	CNC(Address.FLAG_CY, null, Operation.CALL_FLAG_0, 3, "D4", 9),
	CP(Address.FLAG_S, null, Operation.CALL_FLAG_0, 3, "F4", 9),
	CPO(Address.FLAG_P, null, Operation.CALL_FLAG_0, 3, "E4", 9),
	CNZ(Address.FLAG_Z, null, Operation.CALL_FLAG_0, 3, "C4", 9),

	CMA(null, null, Operation.CMA, 1, "2F", 4),
	CMC(null, null, Operation.CMC, 1, "3F", 4),
	DAA(null, null, Operation.DAA, 1, "27", 4),

	CMP_A(Address.REG_A, null, Operation.CMP, 1, "BF", 4),
	CMP_B(Address.REG_B, null, Operation.CMP, 1, "B8", 4),
	CMP_C(Address.REG_C, null, Operation.CMP, 1, "B9", 4),
	CMP_D(Address.REG_D, null, Operation.CMP, 1, "BA", 4),
	CMP_E(Address.REG_E, null, Operation.CMP, 1, "BB", 4),
	CMP_H(Address.REG_H, null, Operation.CMP, 1, "BC", 4),
	CMP_L(Address.REG_L, null, Operation.CMP, 1, "BD", 4),
	CMP_M(Address.REG_M, null, Operation.CMP, 1, "BE", 4),
	CPI(null, null, Operation.CMP, 2, "FE", 7),
	
	DAD_B(Address.REG_B, Address.REG_C, Operation.DAD, 1, "09", 10),
	DAD_D(Address.REG_D, Address.REG_E, Operation.DAD, 1, "19", 10),
	DAD_H(Address.REG_H, Address.REG_L, Operation.DAD, 1, "29", 10),
	DAD_SP(Address.REG_SP1, Address.REG_SP2, Operation.DAD, 1, "39", 10),
	
	DCR_A(Address.REG_A, null, Operation.DCR, 1, "3D", 4),
	DCR_B(Address.REG_B, null, Operation.DCR, 1, "05", 4),
	DCR_C(Address.REG_C, null, Operation.DCR, 1, "0D", 4),
	DCR_D(Address.REG_D, null, Operation.DCR, 1, "15", 4),
	DCR_E(Address.REG_E, null, Operation.DCR, 1, "1D", 4),
	DCR_H(Address.REG_H, null, Operation.DCR, 1, "25", 4),
	DCR_L(Address.REG_L, null, Operation.DCR, 1, "2D", 4),
	DCR_M(Address.REG_M, null, Operation.DCR, 1, "35", 4),
	
	DCX_B(Address.REG_B, Address.REG_C, Operation.DCX, 1, "0B", 6),
	DCX_D(Address.REG_D, Address.REG_E, Operation.DCX, 1, "1B", 6),
	DCX_H(Address.REG_H, Address.REG_L, Operation.DCX, 1, "2B", 6),
	DCX_SP(Address.REG_SP1, Address.REG_SP2, Operation.DCX, 1, "3B", 6),
	
	DI(null, null, Operation.DI, 1, "F3", 4), //Disable interrupts
	EI(null, null, Operation.EI, 1, "FB", 4), //Enable interrupts
	HLT(null, null, Operation.HLT, 1, "76", 4),
	
	IN(null, null, Operation.IN, 2, "DB", 10),
	OUT(null, null, Operation.OUT, 2, "D3", 10),
	
	INR_A(Address.REG_A, null, Operation.INR, 1, "3C", 4),
	INR_B(Address.REG_B, null, Operation.INR, 1, "04", 4),
	INR_C(Address.REG_C, null, Operation.INR, 1, "0C", 4),
	INR_D(Address.REG_D, null, Operation.INR, 1, "14", 4),
	INR_E(Address.REG_E, null, Operation.INR, 1, "1C", 4),
	INR_H(Address.REG_H, null, Operation.INR, 1, "24", 4),
	INR_L(Address.REG_L, null, Operation.INR, 1, "2C", 4),
	INR_M(Address.REG_M, null, Operation.INR, 1, "34", 10),
	
	INX_B(Address.REG_B, Address.REG_C, Operation.INX, 1, "03", 6),
	INX_D(Address.REG_D, Address.REG_E, Operation.INX, 1, "13", 6),
	INX_H(Address.REG_H, Address.REG_L, Operation.INX, 1, "23", 6),
	INX_SP(Address.REG_SP1, Address.REG_SP2, Operation.INX, 1, "33", 6),
	
	JMP(null, null, Operation.JMP, 3, "C3", 10),
	JC(Address.FLAG_CY, null, Operation.JMP_FLAG_1, 3, "DA", 7),
	JM(Address.FLAG_S, null, Operation.JMP_FLAG_1, 3, "FA", 7), // jump if flag S = 1 (call if minus)
	JPE(Address.FLAG_P, null, Operation.JMP_FLAG_1, 3, "EA", 7),
	JZ(Address.FLAG_Z, null, Operation.JMP_FLAG_1, 3, "CA", 7),
	JNC(Address.FLAG_CY, null, Operation.JMP_FLAG_0, 3, "D2", 7),
	JP(Address.FLAG_S, null, Operation.JMP_FLAG_0, 3, "F2", 7),
	JPO(Address.FLAG_P, null, Operation.JMP_FLAG_0, 3, "E2", 7),
	JNZ(Address.FLAG_Z, null, Operation.JMP_FLAG_0, 3, "C2", 7),
	
	LDA(null, null, Operation.LDA, 3, "3A", 13),
	LDAX_B(Address.REG_B, Address.REG_C, Operation.LDA, 1, "0A", 7),
	LDAX_D(Address.REG_D, Address.REG_E, Operation.LDA, 1, "1A", 7),
	LHLD(null, null, Operation.LHLD, 3, "2A", 16),
	
	LXI_B(Address.REG_B, Address.REG_C, Operation.LXI, 3, "01", 10),
	LXI_D(Address.REG_D, Address.REG_E, Operation.LXI, 3, "11", 10),
	LXI_H(Address.REG_H, Address.REG_L, Operation.LXI, 3, "21", 10),
	LXI_SP(Address.REG_SP1, Address.REG_SP2, Operation.LXI, 3, "31", 10),
	
	MOV_A_A(Address.REG_A, Address.REG_A, Operation.MOV, 1, "7F", 4),
	MOV_A_B(Address.REG_A, Address.REG_B, Operation.MOV, 1, "78", 4),
	MOV_A_C(Address.REG_A, Address.REG_C, Operation.MOV, 1, "79", 4),
	MOV_A_D(Address.REG_A, Address.REG_D, Operation.MOV, 1, "7A", 4),
	MOV_A_E(Address.REG_A, Address.REG_E, Operation.MOV, 1, "7B", 4),
	MOV_A_H(Address.REG_A, Address.REG_H, Operation.MOV, 1, "7C", 4),
	MOV_A_L(Address.REG_A, Address.REG_L, Operation.MOV, 1, "7D", 4),
	MOV_A_M(Address.REG_A, Address.REG_M, Operation.MOV, 1, "7E", 7),
	
	MOV_B_A(Address.REG_B, Address.REG_A, Operation.MOV, 1, "47", 4),
	MOV_B_B(Address.REG_B, Address.REG_B, Operation.MOV, 1, "40", 4),
	MOV_B_C(Address.REG_B, Address.REG_C, Operation.MOV, 1, "41", 4),
	MOV_B_D(Address.REG_B, Address.REG_D, Operation.MOV, 1, "42", 4),
	MOV_B_E(Address.REG_B, Address.REG_E, Operation.MOV, 1, "43", 4),
	MOV_B_H(Address.REG_B, Address.REG_H, Operation.MOV, 1, "44", 4),
	MOV_B_L(Address.REG_B, Address.REG_L, Operation.MOV, 1, "45", 4),
	MOV_B_M(Address.REG_B, Address.REG_M, Operation.MOV, 1, "46", 7),
	
	MOV_C_A(Address.REG_C, Address.REG_A, Operation.MOV, 1, "4F", 4),
	MOV_C_B(Address.REG_C, Address.REG_B, Operation.MOV, 1, "48", 4),
	MOV_C_C(Address.REG_C, Address.REG_C, Operation.MOV, 1, "49", 4),
	MOV_C_D(Address.REG_C, Address.REG_D, Operation.MOV, 1, "4A", 4),
	MOV_C_E(Address.REG_C, Address.REG_E, Operation.MOV, 1, "4B", 4),
	MOV_C_H(Address.REG_C, Address.REG_H, Operation.MOV, 1, "4C", 4),
	MOV_C_L(Address.REG_C, Address.REG_L, Operation.MOV, 1, "4D", 4),
	MOV_C_M(Address.REG_C, Address.REG_M, Operation.MOV, 1, "4E", 7),
	
	MOV_D_A(Address.REG_D, Address.REG_A, Operation.MOV, 1, "57", 4),
	MOV_D_B(Address.REG_D, Address.REG_B, Operation.MOV, 1, "50", 4),
	MOV_D_C(Address.REG_D, Address.REG_C, Operation.MOV, 1, "51", 4),
	MOV_D_D(Address.REG_D, Address.REG_D, Operation.MOV, 1, "52", 4),
	MOV_D_E(Address.REG_D, Address.REG_E, Operation.MOV, 1, "53", 4),
	MOV_D_H(Address.REG_D, Address.REG_H, Operation.MOV, 1, "54", 4),
	MOV_D_L(Address.REG_D, Address.REG_L, Operation.MOV, 1, "55", 4),
	MOV_D_M(Address.REG_D, Address.REG_M, Operation.MOV, 1, "56", 7),
	
	MOV_E_A(Address.REG_E, Address.REG_A, Operation.MOV, 1, "5F", 4),
	MOV_E_B(Address.REG_E, Address.REG_B, Operation.MOV, 1, "58", 4),
	MOV_E_C(Address.REG_E, Address.REG_C, Operation.MOV, 1, "59", 4),
	MOV_E_D(Address.REG_E, Address.REG_D, Operation.MOV, 1, "5A", 4),
	MOV_E_E(Address.REG_E, Address.REG_E, Operation.MOV, 1, "5B", 4),
	MOV_E_H(Address.REG_E, Address.REG_H, Operation.MOV, 1, "5C", 4),
	MOV_E_L(Address.REG_E, Address.REG_L, Operation.MOV, 1, "5D", 4),
	MOV_E_M(Address.REG_E, Address.REG_M, Operation.MOV, 1, "5E", 7),
	
	MOV_H_A(Address.REG_H, Address.REG_A, Operation.MOV, 1, "67", 4),
	MOV_H_B(Address.REG_H, Address.REG_B, Operation.MOV, 1, "60", 4),
	MOV_H_C(Address.REG_H, Address.REG_C, Operation.MOV, 1, "61", 4),
	MOV_H_D(Address.REG_H, Address.REG_D, Operation.MOV, 1, "62", 4),
	MOV_H_E(Address.REG_H, Address.REG_E, Operation.MOV, 1, "63", 4),
	MOV_H_H(Address.REG_H, Address.REG_H, Operation.MOV, 1, "64", 4),
	MOV_H_L(Address.REG_H, Address.REG_L, Operation.MOV, 1, "65", 4),
	MOV_H_M(Address.REG_H, Address.REG_M, Operation.MOV, 1, "66", 7),
	
	MOV_L_A(Address.REG_L, Address.REG_A, Operation.MOV, 1, "6F", 4),
	MOV_L_B(Address.REG_L, Address.REG_B, Operation.MOV, 1, "68", 4),
	MOV_L_C(Address.REG_L, Address.REG_C, Operation.MOV, 1, "69", 4),
	MOV_L_D(Address.REG_L, Address.REG_D, Operation.MOV, 1, "6A", 4),
	MOV_L_E(Address.REG_L, Address.REG_E, Operation.MOV, 1, "6B", 4),
	MOV_L_H(Address.REG_L, Address.REG_H, Operation.MOV, 1, "6C", 4),
	MOV_L_L(Address.REG_L, Address.REG_L, Operation.MOV, 1, "6D", 4),
	MOV_L_M(Address.REG_L, Address.REG_M, Operation.MOV, 1, "6E", 7),
	
	MOV_M_A(Address.REG_M, Address.REG_A, Operation.MOV, 1, "77", 4),
	MOV_M_B(Address.REG_M, Address.REG_B, Operation.MOV, 1, "70", 4),
	MOV_M_C(Address.REG_M, Address.REG_C, Operation.MOV, 1, "71", 4),
	MOV_M_D(Address.REG_M, Address.REG_D, Operation.MOV, 1, "72", 4),
	MOV_M_E(Address.REG_M, Address.REG_E, Operation.MOV, 1, "73", 4),
	MOV_M_H(Address.REG_M, Address.REG_H, Operation.MOV, 1, "74", 4),
	MOV_M_L(Address.REG_M, Address.REG_L, Operation.MOV, 1, "75", 4),
	
	MVI_A(Address.REG_A, null, Operation.MOV, 2, "0E", 4),
	MVI_B(Address.REG_B, null, Operation.MOV, 2, "06", 4),
	MVI_C(Address.REG_C, null, Operation.MOV, 2, "1E", 4),
	MVI_D(Address.REG_D, null, Operation.MOV, 2, "16", 4),
	MVI_E(Address.REG_E, null, Operation.MOV, 2, "2E", 4),
	MVI_H(Address.REG_H, null, Operation.MOV, 2, "26", 4),
	MVI_L(Address.REG_L, null, Operation.MOV, 2, "3E", 4),
	MVI_M(Address.REG_M, null, Operation.MOV, 2, "36", 7),
	
	NOP(null, null, Operation.NOP, 1, "00", 4),
	
	ORA_A(Address.REG_A, null, Operation.ORA, 1, "B7", 4),
	ORA_B(Address.REG_B, null, Operation.ORA, 1, "B0", 4),
	ORA_C(Address.REG_C, null, Operation.ORA, 1, "B1", 4),
	ORA_D(Address.REG_D, null, Operation.ORA, 1, "B2", 4),
	ORA_E(Address.REG_E, null, Operation.ORA, 1, "B3", 4),
	ORA_H(Address.REG_H, null, Operation.ORA, 1, "B4", 4),
	ORA_L(Address.REG_L, null, Operation.ORA, 1, "B5", 4),
	ORA_M(Address.REG_M, null, Operation.ORA, 1, "B6", 4),
	ORI(null, null, Operation.ORA, 2, "F6", 7),
	
	PCHL(null, null, Operation.PCHL, 1, "E9", 6),
	
	POP_B(Address.REG_B, Address.REG_C, Operation.POP, 1, "C1", 10),
	POP_D(Address.REG_D, Address.REG_E, Operation.POP, 1, "D1", 10),
	POP_H(Address.REG_H, Address.REG_L, Operation.POP, 1, "E1", 10),
	POP_PSW(Address.REG_A, Address.REG_F, Operation.POP, 1, "F1", 10), // PSW = A + Flags
	
	PUSH_B(Address.REG_B, Address.REG_C, Operation.PUSH, 1, "C5", 12),
	PUSH_D(Address.REG_D, Address.REG_E, Operation.PUSH, 1, "D5", 12),
	PUSH_H(Address.REG_H, Address.REG_L, Operation.PUSH, 1, "E5", 12),
	PUSH_PSW(Address.REG_A, Address.REG_F, Operation.PUSH, 1, "F5", 12),
	
	RAL(null, null, Operation.RAL, 1, "17", 4), //Carry <- Bit[7] ... shift <- ... Bit[0] <- OldCarry
	RAR(null, null, Operation.RAR, 1, "1F", 4), //Carry <- Bit[0] ... shift -> ... Bit[7] <- OldCarry
	RLC(null, null, Operation.RLC, 1, "07", 4), //Carry <- Bit[7] ... Bit[0] <- Bit[7] ... shift <-
	RRC(null, null, Operation.RRC, 1, "0F", 4), //Carry <- Bit[0] ... Bit[7] <- Bit[0] ... shift ->
	
	RET(null, null, Operation.RET, 1, "C9", 10),
	RC(Address.FLAG_CY, null, Operation.RET_FLAG_1, 3, "D8", 6),
	RM(Address.FLAG_S, null, Operation.RET_FLAG_1, 3, "F8", 6), // return if flag S = 1 (call if minus)
	RPE(Address.FLAG_P, null, Operation.RET_FLAG_1, 3, "E8", 6),
	RZ(Address.FLAG_Z, null, Operation.RET_FLAG_1, 3, "C8", 6),
	RNC(Address.FLAG_CY, null, Operation.RET_FLAG_0, 3, "D0", 6),
	RP(Address.FLAG_S, null, Operation.RET_FLAG_0, 3, "F0", 6),
	RPO(Address.FLAG_P, null, Operation.RET_FLAG_0, 3, "E0", 6),
	RNZ(Address.FLAG_Z, null, Operation.RET_FLAG_0, 3, "C0", 6),
	
	RIM(null, null, Operation.RIM, 1, "20", 4),
	SIM(null, null, Operation.SIM, 1, "30", 4),
	
	RST_0(Address.RST_0, null, Operation.RST, 1, "C7", 12),
	RST_1(Address.RST_1, null, Operation.RST, 1, "CF", 12),
	RST_2(Address.RST_2, null, Operation.RST, 1, "D7", 12),
	RST_3(Address.RST_3, null, Operation.RST, 1, "DF", 12),
	RST_4(Address.RST_4, null, Operation.RST, 1, "E7", 12),
	RST_5(Address.RST_5, null, Operation.RST, 1, "EF", 12),
	RST_6(Address.RST_6, null, Operation.RST, 1, "F7", 12),
	RST_7(Address.RST_7, null, Operation.RST, 1, "FF", 12),
	
	SBB_A(Address.REG_A, null, Operation.SBB, 1, "9F", 4),
	SBB_B(Address.REG_B, null, Operation.SBB, 1, "98", 4),
	SBB_C(Address.REG_C, null, Operation.SBB, 1, "99", 4),
	SBB_D(Address.REG_D, null, Operation.SBB, 1, "9A", 4),
	SBB_E(Address.REG_E, null, Operation.SBB, 1, "9B", 4),
	SBB_H(Address.REG_H, null, Operation.SBB, 1, "9C", 4),
	SBB_L(Address.REG_L, null, Operation.SBB, 1, "9D", 4),
	SBB_M(Address.REG_M, null, Operation.SBB, 1, "9E", 4),
	SBI(null, null, Operation.SBB, 2, "DE", 7),
	
	STA(null, null, Operation.STA, 3, "32", 13),
	STAX_B(Address.REG_B, Address.REG_C, Operation.STA, 1, "02", 7),
	STAX_D(Address.REG_D, Address.REG_E, Operation.STA, 1, "12", 7),
	SHLD(null, null, Operation.SHLD, 3, "F9", 16),
	
	STC(null, null, Operation.STC, 1, "37", 4),
	
	SUB_A(Address.REG_A, null, Operation.SUB, 1, "97", 4),
	SUB_B(Address.REG_B, null, Operation.SUB, 1, "90", 4),
	SUB_C(Address.REG_C, null, Operation.SUB, 1, "91", 4),
	SUB_D(Address.REG_D, null, Operation.SUB, 1, "92", 4),
	SUB_E(Address.REG_E, null, Operation.SUB, 1, "93", 4),
	SUB_H(Address.REG_H, null, Operation.SUB, 1, "94", 4),
	SUB_L(Address.REG_L, null, Operation.SUB, 1, "95", 4),
	SUB_M(Address.REG_M, null, Operation.SUB, 1, "96", 4),
	SUI(null, null, Operation.SUB, 2, "D6", 7),
	
	XCHG(Address.REG_D, Address.REG_E, Operation.XCHG, 1, "EB", 4),
	XTHL(Address.REG_SP1, Address.REG_SP2, Operation.XCHG, 1, "E3", 16),
	
	XRA_A(Address.REG_A, null, Operation.XRA, 1, "AF", 4),
	XRA_B(Address.REG_B, null, Operation.XRA, 1, "A8", 4),
	XRA_C(Address.REG_C, null, Operation.XRA, 1, "A9", 4),
	XRA_D(Address.REG_D, null, Operation.XRA, 1, "AA", 4),
	XRA_E(Address.REG_E, null, Operation.XRA, 1, "AB", 4),
	XRA_H(Address.REG_H, null, Operation.XRA, 1, "AC", 4),
	XRA_L(Address.REG_L, null, Operation.XRA, 1, "AD", 4),
	XRA_M(Address.REG_M, null, Operation.XRA, 1, "AE", 4),
	XRI(null, null, Operation.XRA, 2, "EE", 7),
	;

	private Address part1;
	private Address part2;
	private Operation op;
	private byte size;
	private String hex;
	private byte clock;

	private static Map<String, Opcode> BY_NAME;
	private static Map<String, Opcode> BY_HEX;

	static {
		BY_NAME = new HashMap<String, Opcode>();
		BY_HEX = new HashMap<String, Opcode>();
		Opcode[] arr = values();
		for (Opcode oc : arr) {
			//System.out.println("init op " + oc.name() + " " + oc.getHex());
			BY_NAME.put(oc.name(), oc);
			BY_HEX.put(oc.getHex(), oc);
		}
	}

	private Opcode(Address part1, Address part2, Operation op, int size, String hex, int clock) {
		this.part1 = part1;
		this.part2 = part2;
		this.op = op;
		this.size = (byte) size;
		this.hex = hex;
		this.clock = (byte) clock;
	}

	public static Opcode getOpcodeByName(String code) {
		code = code.toUpperCase().replace(" ", "_");
		return BY_NAME.get(code);
	}
	
	public static Opcode getOpcodeByHex(String hex) {
		Opcode op = BY_HEX.get(hex);
		if (op == null) System.out.println("BY_HEX null for hex \"" + hex + "\"");
		return op;
	}

	public Address getAddress1() {
		return part1;
	}

	public Address getAddress2() {
		return part2;
	}

	public Operation getOperation() {
		return op;
	}

	public byte getSize() {
		return size;
	}

	public String getHex() {
		return hex;
	}

	public byte getClock() {
		return clock;
	}
}