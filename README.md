[SCSNMSENG-3050 Smarts Server Tools Order ](https://jira.swisscom.com/browse/SCSNMSENG-3050)

Java Tool
=========

Read the SAM Configuration as exported by Smarts and change sort order.
This Tool should be invoked before the Configuration is 'commited' to GIT (git-hook).

  * sm_config -s SC export -f VM_01_SC.export.xml
  * ...git commit...
  * ./sm_config -s SC import -rf /opt/nme/smarts9/SAM/smarts/local/conf/ics/VM_01_SC.export.xml
