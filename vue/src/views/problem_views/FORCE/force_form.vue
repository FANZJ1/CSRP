<template>
  <div class="main-content" >
    <div class="main-content-inner">
      <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <a href="#" style="float:right" data-container="body" data-toggle="popover" data-placement="bottom" title="tips(再点一下关闭)"
           data-content="这里一共有三个用户：
                admin/123456
                pikachu/000000
                test/abc123">
          点一下提示~
        </a>

      </div>
      <div class="page-content">


        <div class="bf_form">
          <div class="bf_form_main">
            <h4 class="header blue lighter bigger">
              <i class="ace-icon fa fa-coffee green"></i>
              Please Enter Your Information
            </h4>

            <form method="post" action="http://localhost:8080/form">
              <!--            <fieldset>-->
              <label>
                            <span>
                                <input type="text" name="username" placeholder="Username" />
                                <i class="ace-icon fa fa-user"></i>
                            </span>
              </label>

              <label>
                            <span>
                                <input type="password" name="password" placeholder="Password" />
                                <i class="ace-icon fa fa-lock"></i>
                            </span>
              </label>


              <div class="space"></div>

              <div class="clearfix">
                <label><input class="submit" name="submit" type="submit" value="Login" /></label>>
              </div>

              <p  th:text="${message}" th:if="${not#strings.isEmpty(message)}"></p>

            </form>



          </div><!-- /.widget-main -->

        </div><!-- /.widget-body -->

      </div><!-- /.page-content -->
    </div>
  </div><!-- /.main-content -->
</template>

<script>

</script>