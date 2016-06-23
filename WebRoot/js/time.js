        setInterval(s, 1000)
        function s()
        {
            timeStr = new Date().toLocaleString();
          
            document.getElementById("timer").innerHTML = timeStr;
            
        }