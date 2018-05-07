var gulp = require('gulp'),
    connect = require('gulp-connect'),
    concat = require('gulp-concat'),
    compress = require('gulp-yuicompressor'),
    rename = require('gulp-rename'),
    jshint = require('gulp-jshint');

// 监听
gulp.task('watch', function() {
    gulp.watch(['zeroModal.js'], ['jshint']);
    gulp.watch(['lib/*.js', 'zeroModal.js'], ['minifyjs']);
});

//语法检查
gulp.task('jshint', function() {
    return gulp.src('zeroModal.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

//压缩，合并js
gulp.task('minifyjs', function() {
    return gulp.src(['lib/*.js', 'zeroModal.js']) //需要操作的文件
        .pipe(concat('zeroModal.min.js')) //合并所有js到zero-plugin.js
        //.pipe(gulp.dest('dist')) //输出到文件夹
        //.pipe(rename({ suffix: '.min' })) //rename压缩后的文件名
        .pipe(compress({ type: 'js' })) //压缩
        .pipe(gulp.dest('./')); //输出
});


//默认命令，在cmd中输入gulp后，执行的就是这个任务(压缩js需要在检查js之后操作)
gulp.task('default', ['jshint'], function() {
    gulp.start('watch');
});
