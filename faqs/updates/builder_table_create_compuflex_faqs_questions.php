<?php namespace Compuflex\Faqs\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableCreateCompuflexFaqsQuestions extends Migration
{
    public function up()
    {
        Schema::create('compuflex_faqs_questions', function($table)
        {
            $table->engine = 'InnoDB';
            $table->increments('id')->unsigned();
            $table->text('question');
        });
    }
    
    public function down()
    {
        Schema::dropIfExists('compuflex_faqs_questions');
    }
}
