{{/*
Return the proper Docker Image Registry Secret Names
*/}}
{{- define "codecc.imagePullSecrets" -}}
{{- include "common.images.pullSecrets" (dict "images" (list .Values.task.image) "global" .Values.global) -}}
{{- end -}}

{{/*
Create the name of the service account to use
*/}}
{{- define "codecc.serviceAccountName" -}}
{{- if .Values.serviceAccount.create -}}
    {{ default (printf "%s-foo" (include "common.names.fullname" .)) .Values.serviceAccount.name }}
{{- else -}}
    {{ default "default" .Values.serviceAccount.name }}
{{- end -}}
{{- end -}}

{{/*
Create a default fully qualified mongodb subchart.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
*/}}
{{- define "codecc.mongodb.fullname" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- if .Values.mongodb.fullnameOverride -}}
{{- .Values.mongodb.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default "mongodb" .Values.mongodb.nameOverride -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- else -}}
{{- .Values.externalMongodb.host -}}
{{- end -}}
{{- end -}}

{{- define "codecc.redis.fullname" -}}
{{- if .Values.redis.fullnameOverride -}}
{{ $name := .Values.redis.fullnameOverride | trunc 63 | trimSuffix "-"}}
{{- list $name "master" | join "-" -}}
{{- else -}}
{{- $name := default "redis" .Values.redis.nameOverride -}}
{{- printf "%s-%s-master" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}

{{- define "codecc.rabbitmq.fullname" -}}
{{- if .Values.rabbitmq.fullnameOverride -}}
{{- .Values.rabbitmq.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default "rabbitmq" .Values.rabbitmq.nameOverride -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}

{{/*
Return the mongodb username
*/}}
{{- define "codecc.mongodb.username" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- .Values.mongodb.auth.username -}}
{{- else -}}
{{- .Values.externalMongodb.username -}}
{{- end -}}
{{- end -}}

{{/*
Return the mongodb password
*/}}
{{- define "codecc.mongodb.password" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- .Values.mongodb.auth.password -}}
{{- else -}}
{{- .Values.externalMongodb.password -}}
{{- end -}}
{{- end -}}

{{/*
Return the mongodb port
*/}}
{{- define "codecc.mongodb.port" -}}
{{- if eq .Values.mongodb.enabled true -}}
27017
{{- else -}}
{{- .Values.externalMongodb.port -}}
{{- end -}}
{{- end -}}

{{/*
Return the mongodb connection uri
*/}}
{{- define "codecc.defect.mongodbUri" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- printf "mongodb://%s:%s@%s/db_defect?" .Values.mongodb.auth.username .Values.mongodb.auth.password (include "codecc.mongodb.fullname" .) -}}
{{- else -}}
{{- if not (empty .Values.bkCodeccMongoDefectUrl) -}}
{{- .Values.bkCodeccMongoDefectUrl -}}
{{- else -}}
{{- printf "mongodb://%s:%s@%s/db_defect?%s" .Values.externalMongodb.username (.Values.externalMongodb.password | urlquery) (include "codecc.mongodb.fullname" .) .Values.externalMongodb.extraUrlParams -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{- define "codecc.defect.core.mongodbUri" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- printf "mongodb://%s:%s@%s/db_defect?" .Values.mongodb.auth.username .Values.mongodb.auth.password (include "codecc.mongodb.fullname" .) -}}
{{- else -}}
{{- if not (empty .Values.bkCodeccMongoDefectCoreUrl) -}}
{{- .Values.bkCodeccMongoDefectCoreUrl -}}
{{- else -}}
{{- printf "mongodb://%s:%s@%s/db_defect?%s" .Values.externalMongodb.username (.Values.externalMongodb.password | urlquery) (include "codecc.mongodb.fullname" .) .Values.externalMongodb.extraUrlParams -}}
{{- end -}}
{{- end -}}
{{- end -}}


{{- define "codecc.task.mongodbUri" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- printf "mongodb://%s:%s@%s/db_task?" .Values.mongodb.auth.username .Values.mongodb.auth.password (include "codecc.mongodb.fullname" .) -}}
{{- else -}}
{{- if not (empty .Values.bkCodeccMongoTaskUrl) -}}
{{- .Values.bkCodeccMongoTaskUrl -}}
{{- else -}}
{{- printf "mongodb://%s:%s@%s/db_task?%s" .Values.externalMongodb.username (.Values.externalMongodb.password | urlquery) (include "codecc.mongodb.fullname" .) .Values.externalMongodb.extraUrlParams -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{- define "codecc.quartz.mongodbUri" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- printf "mongodb://%s:%s@%s/db_quartz?" .Values.mongodb.auth.username .Values.mongodb.auth.password (include "codecc.mongodb.fullname" .) -}}
{{- else -}}
{{- if not (empty .Values.bkCodeccMongoQuartzUrl) -}}
{{- .Values.bkCodeccMongoQuartzUrl -}}
{{- else -}}
{{- printf "mongodb://%s:%s@%s/db_quartz?%s" .Values.externalMongodb.username (.Values.externalMongodb.password | urlquery) (include "codecc.mongodb.fullname" .) .Values.externalMongodb.extraUrlParams -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{- define "codecc.op.mongodbUri" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- printf "mongodb://%s:%s@%s/db_op?" .Values.mongodb.auth.username .Values.mongodb.auth.password (include "codecc.mongodb.fullname" .) -}}
{{- else -}}
{{- if not (empty .Values.bkCodeccMongoOpUrl) -}}
{{- .Values.bkCodeccMongoOpUrl -}}
{{- else -}}
{{- printf "mongodb://%s:%s@%s/db_op?%s" .Values.externalMongodb.username (.Values.externalMongodb.password | urlquery) (include "codecc.mongodb.fullname" .) .Values.externalMongodb.extraUrlParams -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{- define "codecc.schedule.mongodbUri" -}}
{{- if eq .Values.mongodb.enabled true -}}
{{- printf "mongodb://%s:%s@%s/db_schedule?" .Values.mongodb.auth.username .Values.mongodb.auth.password (include "codecc.mongodb.fullname" .) -}}
{{- else -}}
{{- if not (empty .Values.bkCodeccMongoScheduleUrl) -}}
{{- .Values.bkCodeccMongoScheduleUrl -}}
{{- else -}}
{{- printf "mongodb://%s:%s@%s/db_schedule?%s" .Values.externalMongodb.username (.Values.externalMongodb.password | urlquery) (include "codecc.mongodb.fullname" .) .Values.externalMongodb.extraUrlParams -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/*
Return the mongodb auth ab
*/}}
{{- define "codecc.mongodb.defectAuthDB" -}}
{{- if eq .Values.mongodb.enabled true -}}
db_defect
{{- else -}}
{{- .Values.externalMongodb.authDB -}}
{{- end -}}
{{- end -}}

{{- define "codecc.mongodb.taskAuthDB" -}}
{{- if eq .Values.mongodb.enabled true -}}
db_task
{{- else -}}
{{- .Values.externalMongodb.authDB -}}
{{- end -}}
{{- end -}}

{{- define "codecc.mongodb.quartzAuthDB" -}}
{{- if eq .Values.mongodb.enabled true -}}
db_quartz
{{- else -}}
{{- .Values.externalMongodb.authDB -}}
{{- end -}}
{{- end -}}

{{- define "codecc.redis.host" -}}
{{- if eq .Values.redis.enabled true -}}
{{- (include "codecc.redis.fullname" .) -}}
{{- else -}}
{{- .Values.externalRedis.host -}}
{{- end -}}
{{- end -}}


{{- define "codecc.redis.port" -}}
{{- if eq .Values.redis.enabled true -}}
6379
{{- else -}}
{{- .Values.externalRedis.port -}}
{{- end -}}
{{- end -}}

{{- define "codecc.redis.password" -}}
{{- if eq .Values.redis.enabled true -}}
{{- .Values.redis.auth.password -}}
{{- else -}}
{{- .Values.externalRedis.password -}}
{{- end -}}
{{- end -}}

{{- define "codecc.redis.db" -}}
{{- if eq .Values.redis.enabled true -}}
1
{{- else -}}
{{- .Values.config.bkCodeccRedisDb -}}
{{- end -}}
{{- end -}}

{{- define "codecc.rabbitmq.host" -}}
{{- if eq .Values.rabbitmq.enabled true -}}
{{- include "codecc.rabbitmq.fullname" . -}}
{{- else -}}
{{- .Values.externalRabbitmq.host -}}
{{- end -}}
{{- end -}}

{{- define "codecc.rabbitmq.username" -}}
{{- if eq .Values.rabbitmq.enabled true -}}
{{- .Values.rabbitmq.auth.username -}}
{{- else -}}
{{- .Values.externalRabbitmq.username -}}
{{- end -}}
{{- end -}}

{{- define "codecc.rabbitmq.password" -}}
{{- if eq .Values.rabbitmq.enabled true -}}
{{- .Values.rabbitmq.auth.password -}}
{{- else -}}
{{- .Values.externalRabbitmq.password -}}
{{- end -}}
{{- end -}}

{{- define "codecc.rabbitmq.virtualhost" -}}
{{- if eq .Values.rabbitmq.enabled true -}}
default-vhost
{{- else -}}
{{- .Values.externalRabbitmq.virtualhost -}}
{{- end -}}
{{- end -}}


{{/*
codecc standard labels
*/}}
{{- define "codecc.labels.standard" -}}
helm.sh/chart: {{ include "common.names.chart" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
app.kubernetes.io/name: {{ .Release.Name }}
{{- end -}}

{{/*
Labels to use on deploy.spec.selector.matchLabels and svc.spec.selector
*/}}
{{- define "codecc.labels.matchLabels" -}}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/name: {{ .Release.Name }}
{{- end -}}