rem batch file to collect data from CA MAA using maa_ws.jar, and to push the resulting stuff into CA APM using EPAgentre
rem kopja02@cca.com, all rights reserved CA, Inc 2015
rem set the following parameters:

set EPAgentURL=130.119.30.141:8081
set APMdataType=IntAverage 
set MAAURL="https://mdo.mobility.ca.com"
set MAAtenant="maademo2"
set MAAuser="renja01"
set MAApwd="CAdemo123$"
set MAAperiod="week"
set MAAstart="2015-10-01"
set MAAend="2015-10-23"

@echo off

rem deleting old log files to stop them growing too large

IF EXIST apps.log del /F apps.log
IF EXIST geo.log del /F geo.log
IF EXIST apps_apm.log del /F apps_apm.log
IF EXIST geo_apm.log del /F geo_apm.log

echo ================= Fetching app performance data from CA MAA =============
java -jar maa_ws.jar %MAAURL% %MAAtenant% %MAAuser% %MAApwd% "/mdo/v1/performance/apps_summary" %MAAperiod% %MAAstart% %MAAend% >> apps.log
echo See apps.log for details
java -jar maa_ws.jar %MAAURL% %MAAtenant%  %MAAuser% %MAApwd% "/mdo/v1/usage/geo" %MAAperiod% %MAAstart% %MAAend% >> geo.log
echo See geo.log for details
java -jar maa_ws.jar %MAAURL% %MAAtenant%  %MAAuser% %MAApwd% "/mdo/v1/crashes/crash_summary" %MAAperiod% %MAAstart% %MAAend% >> crashes.log
echo See crashes.log for details
echo ================= Finished fetching data from CA MAA ====================

echo ================= Pushing data to APM EPAgent ===========================
java -Xms54m -Xmx128m -jar apm_ws.jar %EPAgentURL% "appPerformance" %APMdataType% >> apps_apm.log
echo See apps_apm.log for details
java -Xms54m -Xmx128m -jar apm_ws.jar %EPAgentURL% "geo" %APMdataType% >> geo_apm.log
echo See geo_apm.log for details
java -Xms54m -Xmx128m -jar apm_ws.jar %EPAgentURL% "crashes" %APMdataType% >> crashes_apm.log
echo See geo_apm.log for details
echo "================= Finished pushing data to APM EPAgent ==================




