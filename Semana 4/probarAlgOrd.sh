#!/usr/bin/bash

kotlin -cp ".:$(printf %s: libPlotRuntime/*.jar)" PruebaOrdenamientoKt.class $*