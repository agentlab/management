package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Doubles {

	// @formatter:off
	public final static Version v0_0_0_alpha$1 = Version.builder().major(0).minor(0).patch(0).prerelease("alpha").metadata("1").build();
	public final static Version v0_0_0         = Version.builder().major(0).minor(0).patch(0).build();
	public final static Version v0_0_0$1       = Version.builder().major(0).minor(0).patch(0).metadata("1").build();
	public final static Version v0_0_0$2       = Version.builder().major(0).minor(0).patch(0).metadata("2").build();
	public final static Version v0_0_1         = Version.builder().major(0).minor(0).patch(1).build();
	public final static Version v0_1_0         = Version.builder().major(0).minor(1).patch(0).build();
	public final static Version v0_1_1         = Version.builder().major(0).minor(1).patch(1).build();
	public final static Version v0_99_99_rc$1  = Version.builder().minor(99).patch(9).prerelease("rc").metadata("1").build();
	
	public final static Version v1_0_0_alpha$1 = Version.builder().major(1).minor(0).patch(0).prerelease("alpha").metadata("1").build();
	public final static Version v1_0_0_beta$2  = Version.builder().major(1).minor(0).patch(0).prerelease("beta").metadata("2").build();
	public final static Version v1_0_0         = Version.builder().major(1).minor(0).patch(0).build();
	public final static Version v1_0_0$1       = Version.builder().major(1).minor(0).patch(0).metadata("1").build();
	public final static Version v1_0_1_alpha   = Version.builder().major(1).minor(0).patch(1).prerelease("alpha").build();
	public final static Version v1_0_1_alpha1  = Version.builder().major(1).minor(0).patch(1).prerelease("alpha.1").build();
	public final static Version v1_0_1         = Version.builder().major(1).minor(0).patch(1).build();
	public final static Version v1_1_0         = Version.builder().major(1).minor(1).patch(0).build();
	public final static Version v1_1_1         = Version.builder().major(1).minor(1).patch(1).build();
	public final static Version v1_2_0         = Version.builder().major(1).minor(2).patch(0).build();
	public final static Version v1_2_3         = Version.builder().major(1).minor(2).patch(3).build();
	
	public final static Version v2_0_0         = Version.builder().major(2).minor(0).patch(0).build();
	
	public final static Version v1000_0_0      = Version.builder().major(1000).minor(0).patch(0).build();
	// @formatter:on

}
