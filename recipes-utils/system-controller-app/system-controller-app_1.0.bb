DESCRIPTION = "System Contoller App"
SUMMARY = "System Controller App"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://../LICENSE;beginline=1;endline=23;md5=24248f11cbed04b4a7a0609c5d0ff97a"

SRC_URI = "git://gitenterprise.xilinx.com/Platform-Management/system-controller;branch=master;protocol=https \
		   file://LICENSE "

SRCREV="b0ec31c9c5f0ba50844b6fd1de630ffa49fd607c"

inherit update-rc.d

INITSCRIPT_NAME = "system_controller.sh"
INITSCRIPT_PARAMS = "start 99 S ."

S="${WORKDIR}/git"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE_vck-sc-zynqmp = "vck-sc-zynqmp"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "libgpiod"

do_compile(){
	cd ${S}/build/
	oe_runmake
}

do_install(){
	install -d ${D}/usr/bin/
	install -d ${D}${sysconfdir}/init.d/

	cp ${S}/build/sc_app ${D}/usr/bin/
	cp ${S}/src/vccaux_workaround_vck190.sh ${D}/usr/bin/
	cp ${S}/src/system_controller.sh ${D}${sysconfdir}/init.d/
}

FILES_${PN}+="/usr/bin"