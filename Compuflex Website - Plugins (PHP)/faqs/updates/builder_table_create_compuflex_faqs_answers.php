<?php namespace Compuflex\Faqs\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableCreateCompuflexFaqsAnswers extends Migration
{
    public function up()
    {
        Schema::create('compuflex_faqs_answers', function($table)
        {
            $table->engine = 'InnoDB';
            $table->increments('id')->unsigned();
            $table->text('answer');
            $table->integer('question_id')->unsigned();
        });
    }
    
    public function down()
    {
        Schema::dropIfExists('compuflex_faqs_answers');
    }
}
