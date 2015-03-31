package com.yizw.newhouselevy.Entity;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

import com.j256.ormlite.field.DatabaseField;

/**
 * 房屋调查确认表
 */
@SuppressWarnings("serial")
public class HouseConfirm  implements Serializable {

//	private static final long serialVersionUID = 1L;
		
	
	@DatabaseField
	private String id;
	@DatabaseField
	private Date createddate;//创建时间	
	@DatabaseField
	private String creator;//创建人
	@DatabaseField
	private String creatorid;//创建人
	@DatabaseField
	private String modifier;//修改人
	@DatabaseField
	private String modifierid;//修改人id
	@DatabaseField
	private Date modifydate;//修改时间	
	
	
	@DatabaseField
	private String x4;
	@DatabaseField
	private String x1;
	@DatabaseField
	private String x6;
	@DatabaseField
	private String x7;
	@DatabaseField
	private String x8;
	@DatabaseField
	private String x81;
	@DatabaseField
	private String x84;
	@DatabaseField
	private String x200;
	@DatabaseField
	private String x201;
	@DatabaseField
	private Double x9;
	@DatabaseField
	private Double x10;
	@DatabaseField
	private Double x184;
	@DatabaseField
	private Double x188;
	@DatabaseField
	private Double x202;
	@DatabaseField
	private Double x203;
	@DatabaseField
	private Double x11;
	@DatabaseField
	private Double x12;
	@DatabaseField
	private Double x13;
	
	@DatabaseField
	private Double x600;
	
	

	public Double getX5() {
		Double x11 = this.x11==null?0.00:this.x11;
		Double x13 = this.x13==null?0.00:this.x13;
		Double x5 = new BigDecimal(x11 + x13 )
		.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return x5==null?0.00:x5;
	}
	
	public Double getX5_10(){
		Double x9 = this.x9==null?0.00:this.x9;
		Double x10 = this.x10==null?0.00:this.x10;
		Double x184 = this.x184==null?0.00:this.x184;
		Double x188 = this.x188==null?0.00:this.x188;
		Double x202 = this.x202==null?0.00:this.x202;
		Double x203 = this.x203==null?0.00:this.x203;
		Double x11 = this.x11==null?0.00:this.x11;
		Double x5 = new BigDecimal(x9+x10+x184+x188+x202+x203+x11)
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return x5==null?0.00:x5;
	}

	@DatabaseField
	private Double x14;
	@DatabaseField
	private Double x15;
	@DatabaseField
	private Double x16;
	@DatabaseField
	private Double x86;
	@DatabaseField
	private Double x204;
	@DatabaseField
	private Double x205;
	
	
	@DatabaseField
	private String x218;
	
	
	@DatabaseField
	private String c1;
	@DatabaseField
	private String c2;
	@DatabaseField
	private String c3;
	@DatabaseField
	private String c4;
	@DatabaseField
	private String c5;
	@DatabaseField
	private String c6;
	@DatabaseField
	private String c7;
	@DatabaseField
	private String c8;
	@DatabaseField
	private String c9;
	@DatabaseField
	private String c10;
	@DatabaseField
	private String c11;
	@DatabaseField
	private String c12;
	@DatabaseField
	private String c13;
	@DatabaseField
	private String c14;

	@DatabaseField
	private String c15;

	@DatabaseField
	private String c16;

	@DatabaseField
	private String c17;

	@DatabaseField
	private String c18;

	@DatabaseField
	private String c19;

	@DatabaseField
	private String c20;
	@DatabaseField
	private String c21;
	@DatabaseField
	private String c22;

	@DatabaseField
	private String c23;

	@DatabaseField
	private String c24;

	@DatabaseField
	private String c25;

	@DatabaseField
	private String c26;

	@DatabaseField
	private String c27;

	@DatabaseField
	private String c28;

	@DatabaseField
	private String c29;

	@DatabaseField
	private String c30;

	@DatabaseField
	private String c31;

	@DatabaseField
	private String c32;
	@DatabaseField
	private String c33;
	@DatabaseField
	private String c34;

	@DatabaseField
	private String c35;

	@DatabaseField
	private String c36;

	@DatabaseField
	private Double a1;
	@DatabaseField
	private Double a2;
	@DatabaseField
	private Double a3;
	@DatabaseField
	private Double a4;
	@DatabaseField
	private Double a5;
	@DatabaseField
	private Double a6;

	@DatabaseField
	private Double n1;

	@DatabaseField
	private Double n2;

	@DatabaseField
	private Double n3;

	@DatabaseField
	private Double n4;

	@DatabaseField
	private Double n5;

	@DatabaseField
	private Double n6;
	@DatabaseField
	private String pid;
	@DatabaseField
	private String hid;
	@DatabaseField
	private Integer status;
	
	@DatabaseField
	private String x186;
	@DatabaseField
	private String x3;
	@DatabaseField
	private Double x46;
	
    private String e1;
	
	private String e2;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierid() {
		return modifierid;
	}

	public void setModifierid(String modifierid) {
		this.modifierid = modifierid;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public Double getX46() {
		return x46==null?0.00:x46; 
	}

	public void setX46(Double x46) {
		this.x46 = x46;
	}

	public String getX3() {
		return x3;
	}

	public void setX3(String x3) {
		this.x3 = x3;
	}

	public String getX186() {
		return x186;
	}

	public void setX186(String x186) {
		this.x186 = x186;
	}

	public HouseConfirm() {
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getX4() {
		return x4;
	}

	public void setX4(String x4) {
		this.x4 = x4;
	}

	public String getX1() {
		return x1;
	}

	public void setX1(String x1) {
		this.x1 = x1;
	}

	public String getX6() {
		return x6;
	}

	public void setX6(String x6) {
		this.x6 = x6;
	}

	public String getX7() {
		return x7;
	}

	public void setX7(String x7) {
		this.x7 = x7;
	}

	public String getX8() {
		return x8;
	}

	public void setX8(String x8) {
		this.x8 = x8;
	}

	public String getX81() {
		return x81;
	}

	public void setX81(String x81) {
		this.x81 = x81;
	}

	public String getX84() {
		return x84;
	}

	public void setX84(String x84) {
		this.x84 = x84;
	}

	public String getX200() {
		return x200;
	}

	public void setX200(String x200) {
		this.x200 = x200;
	}

	public String getX201() {
		return x201;
	}

	public void setX201(String x201) {
		this.x201 = x201;
	}

	public Double getX9() {
		return x9==null?0.00:x9;
	}

	public void setX9(Double x9) {
		this.x9 = x9;
	}

	public Double getX10() {
		return x10==null?0.00:x10;
	}

	public void setX10(Double x10) {
		this.x10 = x10;
	}

	public Double getX184() {
		return x184==null?0.00:x184;
	}

	public void setX184(Double x184) {
		this.x184 = x184;
	}

	public Double getX188() {
		return x188==null?0.00:x188;
	}

	public void setX188(Double x188) {
		this.x188 = x188;
	}

	public Double getX202() {
		return x202==null?0.00:x202;
	}

	public void setX202(Double x202) {
		this.x202 = x202;
	}

	public Double getX203() {
		return x203==null?0.00:x203;
	}

	public void setX203(Double x203) {
		this.x203 = x203;
	}

	public Double getX11() {
		return x11==null?0.00:x11;
	}

	public void setX11(Double x11) {
		this.x11 = x11;
	}

	public Double getX12() {
		return x12==null?0.00:x12;
	}

	public void setX12(Double x12) {
		this.x12 = x12;
	}

	public Double getX13() {
		return x13==null?0.00:x13;
	}

	public void setX13(Double x13) {
		this.x13 = x13;
	}

	public Double getX14() {
		return x14==null?0.00:x14;
	}

	public void setX14(Double x14) {
		this.x14 = x14;
	}

	public Double getX15() {
		return x15==null?0.00:x15;
	}

	public void setX15(Double x15) {
		this.x15 = x15;
	}

	public Double getX16() {
		return x16==null?0.00:x16;
	}

	public void setX16(Double x16) {
		this.x16 = x16;
	}

	public Double getX86() {
		return x86==null?0.00:x86;
	}

	public void setX86(Double x86) {
		this.x86 = x86;
	}

	public Double getX204() {
		return x204==null?0.00:x204;
	}

	public void setX204(Double x204) {
		this.x204 = x204;
	}

	public Double getX205() {
		return x205==null?0.00:x205;
	}

	public void setX205(Double x205) {
		this.x205 = x205;
	}


	public String getX218() {
		return x218;
	}

	public void setX218(String x218) {
		this.x218 = x218;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	public String getC7() {
		return c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	public String getC8() {
		return c8;
	}

	public void setC8(String c8) {
		this.c8 = c8;
	}

	public String getC9() {
		return c9;
	}

	public void setC9(String c9) {
		this.c9 = c9;
	}

	public String getC10() {
		return c10;
	}

	public void setC10(String c10) {
		this.c10 = c10;
	}

	public String getC11() {
		return c11;
	}

	public void setC11(String c11) {
		this.c11 = c11;
	}

	public String getC12() {
		return c12;
	}

	public void setC12(String c12) {
		this.c12 = c12;
	}

	public String getC13() {
		return c13;
	}

	public void setC13(String c13) {
		this.c13 = c13;
	}

	public String getC14() {
		return c14;
	}

	public void setC14(String c14) {
		this.c14 = c14;
	}

	public String getC15() {
		return c15;
	}

	public void setC15(String c15) {
		this.c15 = c15;
	}

	public String getC16() {
		return c16;
	}

	public void setC16(String c16) {
		this.c16 = c16;
	}

	public String getC17() {
		return c17;
	}

	public void setC17(String c17) {
		this.c17 = c17;
	}

	public String getC18() {
		return c18;
	}

	public void setC18(String c18) {
		this.c18 = c18;
	}

	public String getC19() {
		return c19;
	}

	public void setC19(String c19) {
		this.c19 = c19;
	}

	public String getC20() {
		return c20;
	}

	public void setC20(String c20) {
		this.c20 = c20;
	}

	public String getC21() {
		return c21;
	}

	public void setC21(String c21) {
		this.c21 = c21;
	}

	public String getC22() {
		return c22;
	}

	public void setC22(String c22) {
		this.c22 = c22;
	}

	public String getC23() {
		return c23;
	}

	public void setC23(String c23) {
		this.c23 = c23;
	}

	public String getC24() {
		return c24;
	}

	public void setC24(String c24) {
		this.c24 = c24;
	}

	public String getC25() {
		return c25;
	}

	public void setC25(String c25) {
		this.c25 = c25;
	}

	public String getC26() {
		return c26;
	}

	public void setC26(String c26) {
		this.c26 = c26;
	}

	public String getC27() {
		return c27;
	}

	public void setC27(String c27) {
		this.c27 = c27;
	}

	public String getC28() {
		return c28;
	}

	public void setC28(String c28) {
		this.c28 = c28;
	}

	public String getC29() {
		return c29;
	}

	public void setC29(String c29) {
		this.c29 = c29;
	}

	public String getC30() {
		return c30;
	}

	public void setC30(String c30) {
		this.c30 = c30;
	}

	public String getC31() {
		return c31;
	}

	public void setC31(String c31) {
		this.c31 = c31;
	}

	public String getC32() {
		return c32;
	}

	public void setC32(String c32) {
		this.c32 = c32;
	}

	public String getC33() {
		return c33;
	}

	public void setC33(String c33) {
		this.c33 = c33;
	}

	public String getC34() {
		return c34;
	}

	public void setC34(String c34) {
		this.c34 = c34;
	}

	public String getC35() {
		return c35;
	}

	public void setC35(String c35) {
		this.c35 = c35;
	}

	public String getC36() {
		return c36;
	}

	public void setC36(String c36) {
		this.c36 = c36;
	}

	public Double getA1() {
		return a1==null?0.00:a1;
	}

	public void setA1(Double a1) {
		this.a1 = a1;
	}

	public Double getA2() {
		return a2==null?0.00:a2;
	}

	public void setA2(Double a2) {
		this.a2 = a2;
	}

	public Double getA3() {
		return a3==null?0.00:a3;
	}

	public void setA3(Double a3) {
		this.a3 = a3;
	}

	public Double getA4() {
		return a4==null?0.00:a4;
	}

	public void setA4(Double a4) {
		this.a4 = a4;
	}

	public Double getA5() {
		return a5==null?0.00:a5;
	}

	public void setA5(Double a5) {
		this.a5 = a5;
	}

	public Double getA6() {
		return a6==null?0.00:a6;
	}

	public void setA6(Double a6) {
		this.a6 = a6;
	}

	public Double getN1() {
		return n1==null?0.00:n1;
	}

	public void setN1(Double n1) {
		this.n1 = n1;
	}

	public Double getN2() {
		return n2==null?0.00:n2;
	}

	public void setN2(Double n2) {
		this.n2 = n2;
	}

	public Double getN3() {
		return n3==null?0.00:n3;
	}

	public void setN3(Double n3) {
		this.n3 = n3;
	}

	public Double getN4() {
		return n4==null?0.00:n4;
	}

	public void setN4(Double n4) {
		this.n4 = n4;
	}

	public Double getN5() {
		return n5==null?0.00:n5;
	}

	public void setN5(Double n5) {
		this.n5 = n5;
	}

	public Double getN6() {
		return n6==null?0.00:n6;
	}

	public void setN6(Double n6) {
		this.n6 = n6;
	}

	public Double getX600() {
		return x600==null?0.00:x600;
	}

	public void setX600(Double x600) {
		this.x600 = x600;
	}

	public String getE1() {
		return e1;
	}

	public void setE1(String e1) {
		this.e1 = e1;
	}

	public String getE2() {
		return e2;
	}

	public void setE2(String e2) {
		this.e2 = e2;
	}
	
 
}