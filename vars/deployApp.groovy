def call() {
	sshagent(['Master_Node_SSH_Cred']) {
        sh """
        ssh -o StrictHostKeyChecking=no ubuntu@54.226.138.43 <<EOF
        rm -rf Full_React_App
        git clone -b App_Kube_cluster https://github.com/amgad96/Full_React_App.git
        cd Full_React_App
        sudo kubectl apply -f DB-PersistentVol.yaml
        sudo kubectl apply -f MongoDB-DS.yaml
        sudo kubectl apply -f Backend-DS.yaml
        sudo kubectl apply -f Frontend-DS.yaml
        cd ..
        rm -rf Full_React_App
        EOF
        """
                    }       
}
