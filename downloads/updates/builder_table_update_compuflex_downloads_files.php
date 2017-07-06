<?php namespace Compuflex\Downloads\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableUpdateCompuflexDownloadsFiles extends Migration
{
    public function up()
    {
        Schema::table('compuflex_downloads_files', function($table)
        {
            $table->text('file_desc')->nullable();
            $table->text('file_name')->nullable()->unsigned(false)->default(null)->change();
        });
    }
    
    public function down()
    {
        Schema::table('compuflex_downloads_files', function($table)
        {
            $table->dropColumn('file_desc');
            $table->integer('file_name')->nullable()->unsigned(false)->default(null)->change();
        });
    }
}
