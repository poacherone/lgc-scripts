PrintWriter failedHoldIDs = getReportWriter("failed-holdIDs")
PrintWriter scheduledForUpdating = getReportWriter("scheduled-updates")

File HoldIDs = new File(scriptDir, "Udix_ID.txt")

selectByIds( HoldIDs.readLines() )  { hold ->

def associatedMedia  = hold.graph[1].associatedMedia

    associatedMedia.removeIf { it["marc:publicNote"].contains("Externt magasin / Closed stacks") } 
    
    
    scheduledForUpdating.println("${hold.doc.getURI()}")
    hold.scheduleSave(loud: true, onError: { e ->
        failedUpdating.println("Failed to update ${hold.doc.shortId} due to: $e")
    
    })
  }
  
  
  groovy.lang.MissingMethodException: No signature of method: java.lang.String.removeIf() is applicable for argument types: (Script1$_run_closure1$_closure2) values: [Script1$_run_closure1$_closure2@72861723]