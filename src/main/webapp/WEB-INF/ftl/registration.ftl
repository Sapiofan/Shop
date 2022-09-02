<div id="registration" class="modal">
    <form class="modal-content" action="/" method="post">
        <div class="container">
            <!-- <span onclick="document.getElementById('registration').style.display='none'" class="close">&times;</span> -->
            <button class="up-button" onclick="document.getElementById('registration').style.display='none';
                document.getElementById('login').style.display='block'">Sign In
            </button>
            <button class="up-button active">Sign Up</button>
            <input type="email" class="email" placeholder="Enter Email" name="email" required>
            <input type="password" class="password" placeholder="Enter Password" name="psw" required>
            <input type="password" class="password" placeholder="Repeat Password" name="psw-repeat" required>
            <div class="clearfix">
                <button type="submit" class="signupbtn" name="registration">Sign Up</button>
            </div>
            <p>By creating an account you agree to our <a href="/help#terms" style="color:dodgerblue">Terms &
                    Conditions</a>.</p>
        </div>
    </form>
</div>