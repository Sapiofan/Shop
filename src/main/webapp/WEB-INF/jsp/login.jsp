<div id="login" class="modal">
    <form class="modal-content" action="/" method="post">
        <div class="container">
            <!-- <span onclick="document.getElementById('login').style.display='none'" class="close">&times;</span> -->
            <button class="up-button active">Sign In</button>
            <button class="up-button" onclick="document.getElementById('registration').style.display='block';
                document.getElementById('login').style.display='none'">Sign Up
            </button>
            <input class="email" type="email" placeholder="Enter Email" name="email" id="profileEmail" required>
            <input class="password" type="password" placeholder="Enter Password" name="psw" id="profilePassword" required>
            <div class="clearfix">
                <button type="submit" class="signupbtn" name="login" id="submitLogin">Sign In</button>
            </div>
        </div>
    </form>
</div>