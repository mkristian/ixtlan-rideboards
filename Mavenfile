#-*- mode: ruby -*-
GWT_VERSION = '2.4.0'
jar('de.mkristian.gwt:rails-gwt', '0.7.1-SNAPSHOT').scope :provided
jar('org.fusesource.restygwt:restygwt', '1.3-SNAPSHOT').scope :provided
jar('javax.ws.rs:jsr311-api', '1.1').scope :provided
jar('com.google.gwt:gwt-user', GWT_VERSION).scope :provided
jar('com.google.gwt.inject:gin', '1.5.0').scope :provided
jar('javax.validation:validation-api', '1.0.0.GA').scope :provided
jar('javax.validation:validation-api', '1.0.0.GA', 'sources').scope :provided

plugin('org.codehaus.mojo:gwt-maven-plugin', GWT_VERSION) do |gwt|
  gwt.with({ :warSourceDirectory => "${basedir}/public",
             :webXml => "${basedir}/public/WEB-INF/web.xml",
             :webappDirectory => "${basedir}/public",
             :hostedWebapp => "${basedir}/public",
             :inplace => true,
             :logLevel => "INFO",
             :style => "DETAILED",
             :treeLogger => true,
             :extraJvmArgs => "-Xmx512m",
             :runTarget => "Rideboard.html",
             :includes => "**/RideboardGWTTestSuite.java"
           })
  gwt.executions.goals << ["clean", "compile", "test"]
end
plugin(:rails3).in_phase("initialize").execute_goal(:pom).with :force => true

#-- Macs need the -d32 -XstartOnFirstThread jvm options -->
profile("mac") do |mac|
  mac.activation.os.family "mac"
  mac.plugin('org.codehaus.mojo:gwt-maven-plugin').with(:extraJvmArgs => "-d32 -XstartOnFirstThread -Xmx512m")
end

# to get the restygwt and rails-gwt snapshots
#repository("snapshots") do |snapshot|
#  snapshot.url "http://"
#  snapshot.releases(:enabled => false)
#  snapshot.snapshots(:enabled => true)
#end

# vim: syntax=Ruby
