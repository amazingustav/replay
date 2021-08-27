rootProject.name="replay"

include("main", "domain")

// Input
include("rest")
project(":rest").projectDir = file("input/rest")

// Secondary
include("mysql")
project(":mysql").projectDir = file("output/mysql")
